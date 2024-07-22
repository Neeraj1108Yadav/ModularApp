package com.nano.modularapp.model

data class User(
    val _id: String,
    val account_type: String,
    val country_code: String,
    val daily_goal_mins: Int,
    val dob: String,
    val email: String,
    val med_types: List<Any>,
    val name: String,
    val os: String,
    val tags: List<Any>,
    val userLevel: String,
    val user_goals: List<String>,
    val verified: Boolean
)