package com.example.myschool.Dashboard

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myschool.Activity.AddNotice
import com.example.myschool.Activity.MapsActivity
import com.example.myschool.R

class TeacherDashboard : AppCompatActivity(),SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_dashboard)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        findViewById<LinearLayout>(R.id.ll_add_notice).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AddNotice::class.java
                )

            )

        }
        if (!checkSensor())
            return
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        findViewById<LinearLayout>(R.id.id_maps).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MapsActivity::class.java


                )
            )
        }
    }
    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null) {
            flag = false
        }
        return flag
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0]

        if(values<=4)
            startActivity(
                Intent(
                    this,
                    AddNotice::class.java
                )
            )
        else
         return

    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
    }



