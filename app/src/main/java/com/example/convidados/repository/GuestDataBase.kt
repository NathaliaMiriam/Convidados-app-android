package com.example.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//é o banco de dados ... Aqui eu forneço uma conexão com o banco
class GuestDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    //criação do banco de dados
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Guest (" +
                "id integer primary key autoincrement, " +
                "name text, " +
                "presence integer);")
    }
    //atualização de versão que modifica o que for necessário
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}
