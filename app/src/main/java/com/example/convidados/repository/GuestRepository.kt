package com.example.convidados.repository

import android.content.Context
import com.example.convidados.GuestModel

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

}
