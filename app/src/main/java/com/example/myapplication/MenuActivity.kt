package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.support.design.widget.BottomNavigationView

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<BottomNavigationView>(R.id.navegacao)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nova_viagem -> createFragment(nova_viagem())

                    else -> false
                }
            }
    }

    private fun createFragment(f: android.support.v4.app.Fragment): Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_principal, f)
            .addToBackStack(null)
            .commit()
        return true
    }
}
