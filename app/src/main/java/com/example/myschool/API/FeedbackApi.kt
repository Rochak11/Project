package com.example.myschool.API

import com.example.myschool.data.Message
import com.example.myschool.data.MessageListResponse
import com.example.myschool.data.MessageResponse
import retrofit2.Response
import retrofit2.http.*

interface FeedbackApi {
          @POST("feedback")
            suspend fun addFeedback(
               @Header("Authorization") token: String,


               @Body message: Message
          ): Response<MessageResponse>

    @GET("feedback")
    suspend fun getAllFeedback(@Header("Authorization") token: String): Response<MessageListResponse>


    @GET("/{id}")
    suspend fun getFeedbackID(
        @Header("Authorization") token: String,
        @Query("id") id: String
    ): Response<MessageResponse>
}

