package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.dao.Database
import com.example.myapplication.domain.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // esconder o action bar
        supportActionBar?.hide()
        executar()
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {

            val edUsuario = findViewById<EditText>(R.id.edUsuario)
            val edSenha = findViewById<EditText>(R.id.edSenha)
            val contatoDao = Database.getInstance(this@MainActivity).contatoDao()
            GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine in background and continue
                var contato = withContext(Dispatchers.IO) {
                    contatoDao.findByUsernamePassword(edUsuario.text.trim().toString(), edSenha.text.trim().toString() );
                }
                Toast.makeText( this@MainActivity, contato.toString(), Toast.LENGTH_SHORT).show()
                if ( contato > 0 ) {
                    val menuIntent = Intent(this@MainActivity,
                        MenuActivity::class.java)
                    menuIntent.putExtra("usuario",
                        edUsuario.text.toString())

                    startActivity(menuIntent)

                }
                else {
                    loginInvalido()
                }
            }
        }
    }

    fun loginInvalido(){

        AlertDialog.Builder(this@MainActivity)
            .setTitle("Login inválido")
            .setMessage("Usuário/Senha inválidos")
            .setPositiveButton("Ok", {dialog, i -> dialog.dismiss() })
            .show()
    }

    fun executar() {
        GlobalScope.launch(Dispatchers.Main) {
            val contatos = withContext(Dispatchers.IO) {
                val contatoDao = Database.getInstance(this@MainActivity).contatoDao()
                val contato = Login("daniel", "123")
                contatoDao.inserir(contato)
            }
        }
    }
}
