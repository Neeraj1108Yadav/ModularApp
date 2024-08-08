package com.nano.modularapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.nano.modularapp.R
import com.nano.modularapp.databinding.FragmentLoginBinding
import com.nano.modularapp.model.UserResponse
import com.nano.modularapp.network.NetworkResult
import com.nano.modularapp.validation.ValidationState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentLogin : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    var errorMsg: ObservableField<String> = ObservableField("")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        binding.login = this
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeInputFieldsError()
        observeUserLogin()
        setListener()
    }

    private fun observeInputFieldsError(){
        viewModel.inputFieldError.observe(viewLifecycleOwner, Observer { state->
            when(state){
                is ValidationState.EmailError->{
                    errorMsg.set(getString(state.error))
                }
                is ValidationState.PasswordError->{
                    errorMsg.set(getString(state.error))
                }
                ValidationState.Success->{}
            }
        })
    }

    private fun observeUserLogin(){
        viewModel.userLogin.observe(viewLifecycleOwner, Observer { result->
            when(result){
                is NetworkResult.Success->{
                    navigateToHome(result.data!!)
                }
                is NetworkResult.Failure->{
                    errorMsg.set(result.message)
                }
                is NetworkResult.Loading->{

                }
            }
        })
    }

    private fun setListener(){

        binding.navigateToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentSignUp)
        }

        binding.btnLogin.setOnClickListener {
            viewModel.loginUser(binding.editTextEmail.text.toString(),binding.editTextPassword.text.toString())
        }
    }

    private fun navigateToHome(userResponse: UserResponse){
        val action = FragmentLoginDirections.actionLoginFragmentToFragmentHome(userResponse.user.email)
        view?.findNavController()?.navigate(action)
    }
}