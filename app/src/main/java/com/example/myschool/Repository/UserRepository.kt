
package com.example.schoolapp.Repository

import com.example.myschool.API.MyAPiRequest
import com.API.UserAPI
import com.example.schoolapp.API.ServiceBuilder
import com.example.schoolapp.data.LoginResponse
import com.example.schoolapp.data.User

class UserRepository :
        MyAPiRequest() {

    private val userAPI = ServiceBuilder.buildService(UserAPI::class.java)

    //REgister USer
    suspend fun registerUser(user: User): LoginResponse {
        return apiRequest {
            userAPI.registerUser(user)
        }
    }

    suspend fun loginUser(username: String, password: String): LoginResponse {
        return apiRequest {
            userAPI.checkUser(username, password)
        }
    }
}