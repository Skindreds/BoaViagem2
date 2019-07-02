package com.example.myapplication.dao

import androidx.room.*
import com.example.myapplication.domain.Destino

@Dao
interface DestinoDao {

    @Insert
    fun inserir(destino: Destino)

    @Insert
    fun inserir(destino: List<Destino>)

    @Update
    fun update(destino: Destino)

    @Delete
    fun delete(destino: Destino)

    @Query("select * from Destino order by nome")
    fun findAll(): List<Destino>

    @Query("select * from Destino where id=:id")
    fun findById(id: Int): Destino





}