package com.example.myschool.Activity

import Notification.NotificationChannel
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myschool.R
import com.example.myschool.Repository.NoticeRepository
import com.example.myschool.data.Message
import com.example.schoolapp.API.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddNotice : AppCompatActivity() {


    private lateinit var edTitle: EditText
    private lateinit var edMessage: EditText
    private lateinit var btnAddNotice: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notice)


        edTitle = findViewById(R.id.et_title)
        edMessage = findViewById(R.id.et_message)
        btnAddNotice = findViewById(R.id.btnAddfeedback)



        btnAddNotice.setOnClickListener { addNotice()
            showHighPriorityNotification()
        }

    }

    private fun showHighPriorityNotification() {
        val notificationManager = NotificationManagerCompat.from(this)

        val notificationChannels = NotificationChannel(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)

            .setContentTitle("Notice Add Vayo Hai Bro!!")
            .setContentText("Notification Ayooo Haii")
            .setColor(Color.BLUE)
            .build()

        notificationManager.notify(1, notification)

    }

    private fun addNotice() {
        val message = Message(edTitle.text.toString(), edMessage.text.toString())
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = NoticeRepository()
                val response = ServiceBuilder.token?.let { repository.addPost(it, message) }
                if (response != null) {
                    if (response.success) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AddNotice,
                                "Notice successfully posted", Toast.LENGTH_SHORT
                            ).show()
                            edTitle.text.clear()
                            edMessage.text.clear()
                        }
                    }
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@AddNotice,
                        "Failed to post notice", Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }
}