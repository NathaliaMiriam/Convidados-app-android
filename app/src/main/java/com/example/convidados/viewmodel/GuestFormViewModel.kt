package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    //instancia a classe GuestRepository e chama getInstance()
    private val repository = GuestRepository.getInstance(application)

    //para observar os dados de um convidado
    private val guestModel = MutableLiveData<GuestModel>()

    //var a ser observada e receber o valor de um convidado --> fun observe na GuestFormActivity
    val guest: LiveData<GuestModel> = guestModel


    //me diz se eu devo chamar a insert(inserção de um convidado) ou a update(atualização de um convidado) --> instanciada lá na GuestFormActivity
    fun save(guest: GuestModel) {
        if (guest.id == 0) { //se for igual a zero, chama a inserção
            repository.insert(guest)
        } else { //caso contrário, chama a atualização
            repository.update(guest)
        }
    }

    //para retornar somente um convidado --> 'get' está implementado em GuestRepository
    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }
}
