package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.dao.Database
import com.example.myapplication.domain.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadastrarUsuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(applicationContext, "Teste", Toast.LENGTH_SHORT)
        setContentView(R.layout.activity_cadastrar_usuario)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)
        btnCadastrar.setOnClickListener {

            val edUsuario = findViewById<EditText>(R.id.edUsuario)
            val edSenha = findViewById<EditText>(R.id.edSenha)
            val contatoDao = Database.getInstance(this@CadastrarUsuario).contatoDao()

            if(edUsuario.text.trim().toString() != "" && edSenha.text.trim().toString() != "") {

                GlobalScope.launch(Dispatchers.Main) {
                    // launch a new coroutine in background and continue
                    var contato = withContext(Dispatchers.IO) {
                        contatoDao.findByUsername(
                            edUsuario.text.trim().toString()
                        );
                    }
                    if (contato > 0) {
                        Toast.makeText(applicationContext, "Usuário já cadastrado.", Toast.LENGTH_SHORT).show()
                        edUsuario.text.clear()
                    } else {
                        GlobalScope.launch(Dispatchers.Main) {
                            var context = withContext(Dispatchers.IO) {
                                var login = Login(edUsuario.text.trim().toString(), edSenha.text.trim().toString())
                                contatoDao.inserir(login)
                            }
                        }
                        finish()
                    }
                }
            } else {
                Toast.makeText(this@CadastrarUsuario, "Informe todos os campos para prosseguir", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
