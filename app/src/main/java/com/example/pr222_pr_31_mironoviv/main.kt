package com.example.pr222_pr_31_mironoviv

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.json.JSONObject
import java.util.Calendar


class main : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    private val MY_SETTINGS = "my_settings"
    lateinit var currentDate: TextView
    var date = Calendar.getInstance()
    val d =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            date.set(Calendar.YEAR, year)
            date.set(Calendar.MONTH, monthOfYear)
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setInitialDateTime()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)
        currentDate = findViewById(R.id.currentDate)
        setInitialDateTime()
    }

    fun Search(view: View) {

        var key = "5GWMMewuCv7woXXuMg2wz0VOjcCDzjTIJNdviyR6"
        var url = "https://api.nasa.gov/planetary/apod?api_key=" + key + "&date=" + currentDate.text

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET,
            url,
            {
                    responce ->
                val obj = JSONObject(responce)

                var url = obj.getString("url")
                pref.edit().putString("url", url).commit()

                var title = obj.getString("title")
                pref.edit().putString("title", title).commit()

                var date1 = obj.getString("date")
                pref.edit().putString("date", date1).commit()

                var exp = obj.getString("explanation")
                pref.edit().putString("exp", exp).commit()

                val intent = Intent(this, result::class.java)
                startActivity(intent)
            },
            {
                Snackbar.make(view, R.string.warn1, Snackbar.LENGTH_LONG).show()
            }
        )
        queue.add(stringRequest)

    }
    fun gotoaut(view: View) {
        pref.edit().putBoolean("IsAuthorized", false).commit()
        val intent = Intent(this, Authorization::class.java)
        startActivity(intent)
    }
    fun setDate(view: View) {
        DatePickerDialog(this, d,
            date.get(Calendar.YEAR),
            date.get(Calendar.MONTH),
            date.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun setInitialDateTime() {
        currentDate.setText(
            DateFormat.format("yyyy-MM-dd", date.getTimeInMillis())
        )
    }

}