package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<BottomNavigationView>(R.id.navegacao)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nova_viagem -> createFragment(nova_viagem())
                    R.id.novo_gasto -> createFragment(novo_gasto())
                    R.id.minhas_viagens -> createFragment(minhas_viagens())
                    R.id.config -> createFragment(config())
                    else -> false
                }
            }
    }

    private fun createFragment(f: Fragment): Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_principal, f)
            .addToBackStack(null)
            .commit()
        return true
    }
}
