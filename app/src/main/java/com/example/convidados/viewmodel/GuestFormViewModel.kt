package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    //instancia a classe GuestRepository e chama getInstance()
    private val repository = GuestRepository.getInstance(application)

    //vincula os dados com a GuestFormActivity | GuestModel instanciada lá na GuestFormActivity
    fun insert(guest: GuestModel) {
        repository.insert(guest)
    }
}
