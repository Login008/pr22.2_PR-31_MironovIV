package com.example.pr222_pr_31_mironoviv

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson

data class User(var pass: String)

class registration : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    lateinit var login : EditText
    lateinit var password : EditText
    lateinit var checkpass : EditText
    private val MY_SETTINGS = "my_settings"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        login = findViewById<EditText>(R.id.editTextLogin)
        password = findViewById<EditText>(R.id.editTextTextPassword)
        checkpass = findViewById<EditText>(R.id.editTextCheckPass)
        pref = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)
    }

    fun Register(view: View) {
        if (login.text.toString().isNotEmpty() and password.text.toString().isNotEmpty() and checkpass.text.toString().isNotEmpty()) {
            if (password.text.toString() == checkpass.text.toString()) {

                val ed = pref.edit()

                var user = User(password.text.toString())
                var pass: String = Gson().toJson(user)

                ed.putString("${login.text}", pass)
                ed.commit()
                val intent = Intent(this, Authorization::class.java)
                startActivity(intent)
            }
            else
            {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("Пароль и подтверждение не совпадает")
                    .setPositiveButton("Ok", null)
                    .create()
                    .show()
            }
        }
        else
        {
            val alert = AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("У вас есть незаполненные поля")
                .setPositiveButton("Ok", null)
                .create()
                .show()
        }
    }
}