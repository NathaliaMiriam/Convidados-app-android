package com.example.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.convidados.constants.DataBaseConstants

//é o banco de dados ... Aqui eu forneço uma conexão com o banco
class GuestDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    //criação do banco de dados
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " (" +
                    DataBaseConstants.GUEST.COLUMNS.ID + "integer primary key autoincrement, " +
                    DataBaseConstants.GUEST.COLUMNS.NAME + "text, " +
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE + "integer);"
        )
    }

    //atualização de versão que modifica o que for necessário
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}
