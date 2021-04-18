package com.example.myschool.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myschool.R
import com.example.myschool.Repository.FeedbackRepository
import com.example.myschool.data.Message
import com.example.schoolapp.API.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Addfeedback : AppCompatActivity() {



    private lateinit var ed_title: EditText
    private lateinit var ed_message: EditText
    private lateinit var btnAddfeedback: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addfeedback)


        ed_title = findViewById(R.id.et_title)
        ed_message = findViewById(R.id.et_message)
        btnAddfeedback = findViewById(R.id.btnAddfeedback)

        btnAddfeedback.setOnClickListener { addfeedback() }

    }

    private fun addfeedback() {
        val message = Message(ed_title.text.toString(), ed_message.text.toString())
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = FeedbackRepository()
                val response = ServiceBuilder.token?.let { repository.addPost(it, message) }
                if (response != null) {
                    if (response.success) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@Addfeedback,
                                "feedback successfully posted",
                                Toast.LENGTH_SHORT
                            ).show()
                            ed_title.text.clear()
                            ed_message.text.clear()
                        }
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@Addfeedback,
                        "Failed to post feedback", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

