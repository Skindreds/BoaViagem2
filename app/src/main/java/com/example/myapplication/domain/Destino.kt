package com.example.myapplication.domain

import androidx.room.Entity

import androidx.room.PrimaryKey

@Entity
data class Destino(val nome: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}