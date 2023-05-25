package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

//faz a listagem de convidados
//estendi AndroidViewModel() no lugar da ViewModel, pois numa ViewModel não existe contexto e o contexto precisa ser aplicado p q eu consiga instaciar aqui o repositório
class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    //instancia o repositório
    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()

    val guests: LiveData<List<GuestModel>> = listAllGuests

    //faz a listagem e atribui p AllGuestsFragment ... chamando o repositório, que por sua vez, vai no banco e tenta fazer a listagem
    fun getAll() {
        listAllGuests.value = repository.getAll()
    }

    //faz a listagem e atribui p AbsentFragment ... chamando o repositório, que por sua vez, vai no banco e tenta fazer a listagem
    fun getAbsent() {
        listAllGuests.value = repository.getAbsent()
    }

    //faz a listagem e atribui p PresentFragment ... chamando o repositório, que por sua vez, vai no banco e tenta fazer a listagem
    fun getPresent() {
        listAllGuests.value = repository.getPresent()
    }

    //fun chamada em AllGuestsFragment p fazer a remoção de um convidado da listagem
    fun delete(id: Int) {
        repository.delete(id)
    }
}
