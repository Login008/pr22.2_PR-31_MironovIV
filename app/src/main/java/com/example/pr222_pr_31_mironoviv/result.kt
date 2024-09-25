package com.example.pr222_pr_31_mironoviv

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class result : AppCompatActivity() {
    lateinit var image : ImageView
    lateinit var date : TextView
    lateinit var title : TextView
    lateinit var exp : TextView
    private lateinit var pref: SharedPreferences
    private val MY_SETTINGS = "my_settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        image = findViewById(R.id.imageView3)
        date = findViewById(R.id.dateView)
        title = findViewById(R.id.titleView)
        exp = findViewById(R.id.expView)
        pref = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)

        var url = pref.getString("url", "https://avatars.dzeninfra.ru/get-zen_doc/1704699/pub_64d48f4242bca53414b28c40_64e21672a516301379aff2d4/scale_1200")
        var date1 = pref.getString("date", "Ошибка")
        var title1 = pref.getString("title", "Ошибка")
        var exp1 = pref.getString("exp", "Ошибка")

        Picasso.with(this)
            .load(url)
            .resize(360, 250)
            .into(image)
        date.text = date1
        title.text = title1
        exp.text = exp1
    }
}