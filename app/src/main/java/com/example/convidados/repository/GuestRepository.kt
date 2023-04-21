package com.example.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.model.GuestModel

//é o repositório ... Aqui eu insiro e altero os dados, ou seja, manipulo os dados usando a conexão com o banco
class GuestRepository private constructor(context: Context) {

    //instancia o banco de dados
    private val guestDataBase = GuestDataBase(context)

    //singleton
    companion object {
        private lateinit var repository: GuestRepository

        //instancia o repositório
        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    //insere dado/convidado no banco de dados
    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }
}
