package com.example.myschool.Repository

import com.example.myschool.API.MyAPiRequest
import com.example.myschool.API.NoticeApi
import com.example.myschool.data.Message
import com.example.myschool.data.MessageListResponse
import com.example.myschool.data.MessageResponse
import com.example.schoolapp.API.ServiceBuilder

class NoticeRepository : MyAPiRequest() {

    private val noticeApi = ServiceBuilder.buildService(NoticeApi::class.java)

    suspend fun addPost(token: String, message: Message): MessageResponse {
        return apiRequest {
            noticeApi.addNotice(token, message)
        }
    }

    suspend fun getAllNotice(token: String): MessageListResponse {
        return apiRequest {
            noticeApi.getAllNotice(token)
        }
    }

    suspend fun getPostById(token: String, id: String): MessageResponse {
        return apiRequest {
            noticeApi.getNoticeId(token, id)
        }
    }
}