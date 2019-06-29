package com.example.myapplication.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.domain.Login
import kotlin.random.Random

@Database(entities = arrayOf(Login::class), exportSchema = true, version = 2)
abstract class Database() : RoomDatabase(){

    abstract fun contatoDao(): LoginDao

    companion object {

        private var instance: com.example.myapplication.dao.Database? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table contato add column fone text not null default ''")
            }

        }

        fun getInstance(context: Context): com.example.myapplication.dao.Database {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    com.example.myapplication.dao.Database::class.java,
                    "dados" + Math.random()
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
            return instance as com.example.myapplication.dao.Database
        }

    }

}