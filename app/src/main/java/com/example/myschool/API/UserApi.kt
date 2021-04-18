package com.API// UserAPI

import com.example.schoolapp.data.LoginResponse
import com.example.schoolapp.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {
    //register user
    @POST("auth/register")
    suspend fun registerUser(@Body user: User): Response<LoginResponse>


    //Login user
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun checkUser(
        @Field("username") username:String,
        @Field("password") password:String
    ):Response<LoginResponse>

}