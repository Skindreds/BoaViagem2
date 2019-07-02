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
import com.example.myapplication.domain.Destino
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
        val btnCadastro = findViewById<Button>(R.id.btnCadastrar)
        btnCadastro.setOnClickListener {
            val edUsuario = findViewById<EditText>(R.id.edUsuario)
            val cadastroIntent = Intent(this,
                CadastrarUsuario::class.java)
                cadastroIntent.putExtra("usuario",
                edUsuario.text.toString())

            startActivity(cadastroIntent)
        }
    }

    fun loginInvalido(){

        AlertDialog.Builder(this@MainActivity)
            .setTitle("Login inválido")
            .setMessage("Usuário/Senha inválidos")
            .setPositiveButton("Ok", {dialog, _ -> dialog.dismiss() })
            .show()
    }

    fun executar() {
        GlobalScope.launch(Dispatchers.Main) {
            val contatos = withContext(Dispatchers.IO) {
                val contatoDao = Database.getInstance(this@MainActivity).contatoDao()
                val contato = Login("daniel", "123")
                contatoDao.inserir(contato)
                val destinoDao = Database.getInstance(this@MainActivity).destinoDao()
                destinoDao.inserir(Destino("Blumenau"))
                destinoDao.inserir(Destino("Gaspar"))
                destinoDao.inserir(Destino("Brusque"))
                destinoDao.inserir(Destino("Timbó"))
                destinoDao.inserir(Destino("Floripa"))
            }
        }
    }
}
