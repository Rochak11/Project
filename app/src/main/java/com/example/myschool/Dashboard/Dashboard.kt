package com.example.myschool.Dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.myschool.Activity.Addfeedback
import com.example.myschool.Activity.MapsActivity
import com.example.myschool.Activity.Notice
import com.example.myschool.Activity.feedback
import com.example.myschool.R

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        findViewById<LinearLayout>(R.id.give).setOnClickListener {
            startActivity(
                Intent(
                    this,
                 Addfeedback::class.java
                )

            )
        }
        findViewById<LinearLayout>(R.id.id_map).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MapsActivity::class.java
                )
            )
        }
}
}