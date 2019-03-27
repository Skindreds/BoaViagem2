package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // esconder o action bar
        supportActionBar?.hide()

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val edUsuario = findViewById<EditText>(R.id.edUsuario)
            val edSenha = findViewById<EditText>(R.id.edSenha)
            if ("fabiano".equals(edUsuario.text.trim().toString()) &&
                "123".equals(edSenha.text.trim().toString())) {
                val menuIntent = Intent(this@MainActivity,
                    MenuActivity::class.java)
                menuIntent.putExtra("usuario",
                    edUsuario.text.toString())

                startActivity(menuIntent)

            }
            else {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Login inválido")
                    .setMessage("Usuário/Senha inválidos")
                    .setPositiveButton("Ok", {dialog, i -> dialog.dismiss() })
                    .show()
            }

        }

    }
}
