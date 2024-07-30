package com.nano.modularapp.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * Created By Neeraj Yadav on 26/07/24
 */
object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:errorText")
    fun setErrorText(view:TextView,errorMsgId:Int?){
        if(errorMsgId != null && errorMsgId != 0){
            view.text = view.context.getString(errorMsgId)
        }else{
            view.text = ""
        }
    }
}