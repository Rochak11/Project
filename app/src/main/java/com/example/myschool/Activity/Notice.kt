package com.example.myschool.Activity

import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myschool.R

class Notice : AppCompatActivity() {
    private lateinit var et_title: EditText
    private lateinit var et_message: EditText
    private lateinit var rvNotice: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        et_title = findViewById(R.id.et_title)
        et_message = findViewById(R.id.et_message)
        rvNotice = findViewById(R.id.rvNotice)

        findViewById<LinearLayout>(R.id.ll_notice).setOnClickListener {

        }
    }
}
