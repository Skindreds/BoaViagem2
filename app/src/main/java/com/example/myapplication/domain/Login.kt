package com.example.myapplication.domain

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = arrayOf(Index(value = ["username"], unique = true)))
data class Login(val username: String, val password: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}