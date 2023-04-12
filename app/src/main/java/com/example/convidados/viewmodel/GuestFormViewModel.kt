package com.example.convidados.viewmodel

import androidx.lifecycle.ViewModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel : ViewModel() {

    //instancia a classe GuestRepository e chama getInstance()
    private val repository = GuestRepository.getInstance()
}
