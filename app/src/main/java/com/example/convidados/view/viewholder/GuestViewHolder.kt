package com.example.convidados.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

//A ViewHolder também tem uma classe abstrata e não consigo instanciá-la na GuestsAdapter ... Por isso, criei aqui uma classe para ela e depois fiz a instancia lá na GuestsAdapter
//É ela que tem a referência para os meus elementos de interface, seja um TextView, uma imagem, um Button etc...

class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
}
