package com.example.myschool.API

import com.example.myschool.data.Message
import com.example.myschool.data.MessageListResponse
import com.example.myschool.data.MessageResponse
import retrofit2.Response
import retrofit2.http.*

interface NoticeApi {

    @POST("notice")
    suspend fun addNotice(
        @Header("Authorization") token: String,


    @Body message: Message
    ): Response<MessageResponse>

    @GET("notice")
    suspend fun getAllNotice(@Header("Authorization") token: String): Response<MessageListResponse>

    @GET("notice/{id}")
    suspend fun getNoticeId(
        @Header("Authorization") token: String,
        @Query("id") id: String
    ): Response<MessageResponse>
}