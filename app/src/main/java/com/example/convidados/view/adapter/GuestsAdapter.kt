package com.example.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.viewholder.GuestViewHolder

//Por não conseguir instanciar o Adapter em AllGuestsFragment, por ter uma classe abstrata... Criei aqui o meu Adapter ...
//ViewHolder é uma outra classe e depois dela implementei os métodos: onCreateViewHolder, getItemCount e onBindViewHolder ... visto que, estou estendendo de Adapter
//A ViewHolder também tem uma classe abstrata e não consigo fazer a instancia aqui... Por isso, criei uma classe para ela --> GuestViewHolder ...

class GuestsAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    //faz a listagem de convidados p posteriormente atribuir o valor de list (List<GuestModel) ... A princípio uma lista vazia
    private var guestList: List<GuestModel> = listOf()

    //faz a criação do layout - cria as linhas que estão dentro da RecyclerView --> row_guest ... dentro é esperado alguém do tipo View ... Após criar, ela chama a onBindViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {

        //infla o layout e espera um elemento de interface (o layout)
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item)
    }

    //tamanho da listagem
    override fun getItemCount(): Int {
        return guestList.count()
    }

    //faz a cola/atribuição de valores p o layout...
    //ela é chamada depois da criação do layout na onCreateViewHolder (q é o holder)... o holder armazena os elementos de interface
    //e a position foi passada em getItemCount, ou seja, ela chama e recebe o que foi passado lá...
    //o bind foi criado em GuestViewHolder... p fazer a ligação do elemento de interface (holder) com os dados

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position]) //(guestList[position]) --> uma posição da lista
    }

    //recebe a lista - recebe as informações
    fun updatedGuests(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged() //notifica a RecyclerView sobre o recebimento de novas atualizações, para q ela possa se atualizar
    }
}
