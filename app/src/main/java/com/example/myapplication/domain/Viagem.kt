package com.example.myapplication.domain

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.sql.Date
import java.time.LocalDate

@Entity
data class Viagem(
    val id_destino: Long,
    val sn_lazer: Int,
    val dt_chegada: String,
    val dt_partida: String,
    val qt_orcamento: Double,
    val qt_pessoas: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}