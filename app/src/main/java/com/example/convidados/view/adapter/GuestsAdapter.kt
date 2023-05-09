package com.example.convidados.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.view.viewholder.GuestViewHolder

//Por não conseguir instanciar o Adapter em AllGuestsFragment, por ter uma classe abstrata... Criei aqui o meu Adapter ...
//ViewHolder é uma outra classe e depois dela implementei os métodos: onCreateViewHolder, getItemCount e onBindViewHolder ... visto que, estou estendendo de Adapter
//A ViewHolder também tem uma classe abstrata e não consigo fazer a instancia aqui... Por isso, criei uma classe para ela --> GuestViewHolder ...

class GuestsAdapter : RecyclerView.Adapter<GuestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
