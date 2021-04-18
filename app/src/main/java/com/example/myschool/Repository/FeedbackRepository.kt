package com.example.myschool.Repository

import com.example.myschool.API.FeedbackApi
import com.example.myschool.API.MyAPiRequest

import com.example.myschool.data.Message
import com.example.myschool.data.MessageListResponse
import com.example.myschool.data.MessageResponse
import com.example.schoolapp.API.ServiceBuilder

class FeedbackRepository: MyAPiRequest() {

    private val  FeedbackApi = ServiceBuilder.buildService(FeedbackApi::class.java)

    suspend fun addPost(token: String, message: Message): MessageResponse {
        return apiRequest {
            FeedbackApi.addFeedback(token, message)
        }
    }

    suspend fun getAllFeedback(token: String): MessageListResponse {
        return apiRequest {
            FeedbackApi.getAllFeedback(token)
        }
    }

    suspend fun getPostById(token: String, id: String): MessageResponse {
        return apiRequest {
            FeedbackApi.getFeedbackID(token, id)
        }
    }
}