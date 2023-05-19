package com.example.convidados.view.viewholder

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.listener.OnGuestListener

//Atribui informações...
//A ViewHolder também tem uma classe abstrata e não consigo instanciá-la na GuestsAdapter ... Por isso, criei aqui uma classe para ela e depois fiz a instancia lá na GuestsAdapter
//É ela que tem a referência para os meus elementos de interface, seja um TextView, uma imagem, um Button etc...

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) { //root --> elemento de interface

    //faz a ligação do elemento de interface (holder --> lá na onBindViewHolder de GuestsAdapter) com os dados
    fun bind(guest: GuestModel) { //o bind me permite ter acesso a tudo que for colocado no layout (row_guest), como imagens, cores, mais textos e etc... Basta atribuir lá os id's e manipular aqui
        bind.textName.text = guest.name //faz a atribuição dos textos/dos valores

        bind.textName.setOnClickListener { //faz a atribuição dos eventos de cliques da listagem
            listener.onClick(guest.id) //ao clicar no convidado terei o id
        }

        //remoção de convidado da listagem
        bind.textName.setOnLongClickListener { //evento de clique longo com lâmbida ... ao clicar e segurar o convidado será removido da lista

            //cria a notificação que aparece na aplicação p q o usuário tome a ação de remoção ou não de um convidado
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja seguir com a remoção?")
                .setPositiveButton("sim") { dialog, which ->
                    listener.onDelete(guest.id) //se clicar em sim, dispara o onDelete() e a remoção acontece
                }
                .setNegativeButton("não", null) //se clicar em não, nada será feito
                .create()
                .show()
            true //retorno do OnLongClickListener é true
        }
    }
}
