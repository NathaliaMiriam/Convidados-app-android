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

    //para observar quando houver atualizações na lista de convidados, como inserção ou edição de um convidado
    private val _saveGuest = MutableLiveData<String>()
    //var a ser observada e receber as atualizações da lista
    val saveGuest: LiveData<String> = _saveGuest //recebe o retorno de insert e update, ou seja, recebe as atualizações da lista


    //me diz se eu devo chamar a insert(inserção de um convidado) ou a update(edição de um convidado) --> instanciada lá na GuestFormActivity
    fun save(guest: GuestModel) {
        if (guest.id == 0) { //se for igual a zero é uma inserção...
            if (repository.insert(guest)) { //verifica se a inserção deu certo, se tiver dado, faz a inserção...
                _saveGuest.value = "Inserção realizada com sucesso!" //permite o retorno do insert p o _saveGuest e mostra a mensagem ao usuário
            } else { //caso a inserção não de certo...
                _saveGuest.value = "Houve uma falha!" //mostra a mensagem de falha
            }
        } else { //caso seja diferente de zero é uma edição
                if (repository.update(guest)) { //verifica se a edição deu certo, se tiver dado, faz a edição...
                    _saveGuest.value = "Edição realizada com sucesso!" //permite o retorno do update p o _saveGuest e mostra a mensagem ao usuário
                } else { //caso a edição não de certo...
                    _saveGuest.value = "Houve uma falha!" //mostra a mensagem de falha
                }
        }
    }

    //para retornar somente um convidado --> 'get' está implementado em GuestRepository
    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }
}
