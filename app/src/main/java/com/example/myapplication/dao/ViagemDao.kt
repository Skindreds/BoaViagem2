package com.example.myapplication.dao

import androidx.room.*
import com.example.myapplication.domain.Viagem

@Dao
interface ViagemDao {

    @Insert
    fun inserir(viagem: Viagem)

    @Insert
    fun inserir(viagem: List<Viagem>)

    @Update
    fun update(viagem: Viagem)

    @Delete
    fun delete(viagem: Viagem)

    @Query("select * from Viagem order by dt_chegada, dt_partida")
    fun findAll(): List<Viagem>

    @Query("select * from Viagem where id=:id")
    fun findById(id: Int): Viagem





}