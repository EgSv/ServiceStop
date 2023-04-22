package ru.startandroid.develop.servicestop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onClickStart(v: View?) {
        startService(Intent(this, MyService::class.java).putExtra("time", 7))
        startService(Intent(this, MyService::class.java).putExtra("time", 2))
        startService(Intent(this, MyService::class.java).putExtra("time", 4))
    }
}