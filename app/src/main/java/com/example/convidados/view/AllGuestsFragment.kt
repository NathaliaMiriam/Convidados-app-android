package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.FragmentAllGuestsBinding
import com.example.convidados.view.adapter.GuestsAdapter
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestsViewModel

    //instancia o Adapter como item desta classe/desta fragment
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {

        //instancia a ViewModel
        viewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        //define o layout da RecyclerView
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //define e instancia o Adapter --> GuestsAdapter --> val adapter (colocada ali em cima)
        binding.recyclerAllGuests.adapter = adapter //faz a cola

        //recebe a fun attachListener da GuestsAdapter
        val listener = object : OnGuestListener { //classe anônima que implementa a interface e implementa os membros (onClick e onDelete) conforme abaixo
            override fun onClick(id: Int) {

                //edição de um convidado
                val intent = Intent(context, GuestFormActivity::class.java) //cria o intent

                val bundle = Bundle() //pega um bundle
                bundle.putInt(DataBaseConstants.GUEST.ID, id) //'putInt' pois estou recebendo um id --> DataBaseConstants.GUEST.ID(caminho p a chave) | id(é o valor)

                intent.putExtras(bundle) //fornece um pacote de informações para a minha intent

                startActivity(intent) //passa o intent e inicializa a Activity com o pacote de informações a mais (bundle)

                //Toast.makeText(context, "Alow, fui clicado!!", Toast.LENGTH_SHORT).show() //ao clicar no convidado a mensagem aparecerá...
            }

            //atribuição da fun delete p a remoção de um convidado da listagem --> AllGuestsViewModel
            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll() //chama a listagem após a remoção do convidado
            }
        }

        //passa a implementação
        adapter.attachListener(listener)

        observe()

        return binding.root
    }

    //chama e atualiza a lista de convidados
    override fun onResume() {
        super.onResume()
        viewModel.getAll() //atribuição da lista de convidados --> AllGuestsViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //obtém a ViewModel e observa a lista de convidados --> AllGuestsViewModel
    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            //passa a lista - a fun updatedGuest está em GuestsAdapter
            adapter.updatedGuests(it)
        }
    }
}
