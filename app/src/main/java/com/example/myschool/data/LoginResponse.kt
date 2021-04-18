package com.example.schoolapp.data

data class LoginResponse(
    var success: Boolean,
    var token: String,
    var message: String,
    var data: User
)