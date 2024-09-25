package com.example.pr222_pr_31_mironoviv

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson

class Authorization : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    lateinit var login : EditText
    lateinit var password : EditText
    private val MY_SETTINGS = "my_settings"
    data class User(var pass: String)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        login = findViewById<EditText>(R.id.editTextLogin)
        password = findViewById<EditText>(R.id.editTextTextPassword)
        pref = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)

        if (pref.getBoolean("IsAuthorized", false) == true)
        {
            val intent = Intent(this, main::class.java)
            startActivity(intent)
        }
    }

    fun SignIn(view: View) {
        if (login.text.toString().isNotEmpty() and password.text.toString().isNotEmpty()) {

            var pass = pref.getString("${login.text}", null)
            var user: User
            try {
                user = Gson().fromJson(pass, User::class.java)
            } catch (e:Exception)
            {
                user = User("")
            }
            if (password.text.toString() == user.pass) {
                var ed = pref.edit()
                ed.putBoolean("IsAuthorized", true)
                ed.commit()

                val intent = Intent(this, main::class.java)
                startActivity(intent)
            } else {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("Неверный логин или пароль")
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

    fun gotoregister(view: View) {
        val intent = Intent(this, registration::class.java)
        startActivity(intent)
    }
}