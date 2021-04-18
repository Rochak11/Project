package com.example.myschool.Activity

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myschool.Dashboard.Dashboard
import com.example.myschool.Dashboard.TeacherDashboard
import com.example.myschool.R
import com.example.schoolapp.API.ServiceBuilder
import com.example.schoolapp.Repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private var sensorManager: SensorManager? = null
    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f
    private lateinit var imageview: ImageView

    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var btn1: Button
    private lateinit var btn2: Button



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        Objects.requireNonNull(sensorManager)!!.registerListener(sensorListener, sensorManager!!
            .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        acceleration = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH

        imageview = findViewById(R.id.imageview)
        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)






        btn2.setOnClickListener {
            val significant = Intent(this, Signup::class.java)
            startActivity(significant)
        }
        btn1.setOnClickListener {
            login()

        }




    }



    private fun login() {
        val username = et1.text.toString()
        val password = et2.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.loginUser(username, password)
                if (response.success) {
                    ServiceBuilder.token = "Bearer " + response.token

                    if (response.data.user_type == "Parent") {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                Dashboard::class.java
                            )
                        )
                    } else {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                TeacherDashboard::class.java
                            )
                        )
                    }
                    finish()

                }

            } catch (ex: Exception) {
                ex.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "Login failed", Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }
    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcceleration = currentAcceleration
            currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta
            if (acceleration > 12) {
                login()

            }
        }
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }
    override fun onResume() {
        sensorManager?.registerListener(sensorListener, sensorManager!!.getDefaultSensor(
            Sensor .TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
        )
        super.onResume()
    }
    override fun onPause() {
        sensorManager!!.unregisterListener(sensorListener)
        super.onPause()
    }
}




