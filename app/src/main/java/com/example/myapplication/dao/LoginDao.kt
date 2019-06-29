package com.example.myapplication.dao

import androidx.room.*
import com.example.myapplication.domain.Login

@Dao
interface LoginDao {

    @Insert
    fun inserir(login: Login)

    @Insert
    fun inserir(login: List<Login>)

    @Update
    fun update(login: Login)

    @Delete
    fun delete(login: Login)

    @Query("select * from Login order by username")
    fun findAll(): List<Login>

    @Query("select * from Login where id=:id")
    fun findById(id: Int): Login

    @Query("select COUNT(*) from Login where username=:username and password=:password")
    fun findByUsernamePassword(username: String, password: String): Int




}