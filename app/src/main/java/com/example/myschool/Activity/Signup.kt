package com.example.myschool.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myschool.Dashboard.Dashboard
import com.example.myschool.R
import com.example.schoolapp.API.ServiceBuilder
import com.example.schoolapp.Repository.UserRepository
import com.example.schoolapp.data.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Signup : AppCompatActivity() {
    private lateinit var imageview1: ImageView
    private lateinit var etFirstName: EditText
    private lateinit var etSecondName: EditText
    private lateinit var etUsername1: EditText
    private lateinit var etPassword1: EditText
    private lateinit var rbParent: RadioButton
    private lateinit var rbFaculty: RadioButton
    private lateinit var btnsignup: Button
    lateinit var radioGroupUserType: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        imageview1 = findViewById(R.id.imageview1)
        etFirstName = findViewById(R.id.etFirstName)
        etSecondName = findViewById(R.id.etSecondName)
        etUsername1 = findViewById(R.id.etUsername1)
        etPassword1 = findViewById(R.id.etPassword1)
        rbParent = findViewById(R.id.rbParent)
        rbFaculty = findViewById(R.id.rbFaculty)
        btnsignup = findViewById(R.id.btnsignup)
        radioGroupUserType = findViewById(R.id.radio_group_user_type)

        rbParent.isChecked = true

        btnsignup.setOnClickListener { signUp() }
    }

    private fun signUp() {
        val fName = etFirstName.text.toString()
        val lName = etSecondName.text.toString()
        val username = etUsername1.text.toString()
        val password = etPassword1.text.toString()

        var userType = "Parent"
        if (rbFaculty.isChecked) {
            userType = "Faculty"
        }

        val user = User(fName, lName, username, password, userType)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.registerUser(user = user)
                if (response.success) {
                    ServiceBuilder.token = "Bearer " + response.token
                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            this@Signup,
                            "Signup successful", Toast.LENGTH_SHORT
                        ).show()
                    }
                    startActivity(
                        Intent(
                            this@Signup,
                            MainActivity::class.java
                        )
                    )
                    finish()
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@Signup,
                        "Signup failed", Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }

}