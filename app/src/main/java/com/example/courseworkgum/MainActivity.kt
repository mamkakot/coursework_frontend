package com.example.courseworkgum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var myBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myBtn = findViewById(R.id.button)

        myBtn?.setOnClickListener {
            Toast.makeText(this, "sas", Toast.LENGTH_SHORT).show()
        }
    }
}