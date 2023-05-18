package com.example.convidados.view.listener

//eventos de clique da listagem e remoção de convidado

interface OnGuestListener {
    fun onClick(id: Int)
    fun onDelete(id: Int)
}
