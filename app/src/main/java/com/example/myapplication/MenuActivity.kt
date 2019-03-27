package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val txtUsuario = findViewById<TextView>(R.id.txtUsuario)
        txtUsuario.text = intent.getStringExtra("usuario")
    }
}
