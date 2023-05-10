package com.example.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.databinding.FragmentAllGuestsBinding
import com.example.convidados.view.adapter.GuestsAdapter
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


        //atribuição da lista de convidados --> AllGuestsViewModel
        viewModel.getAll()

        observe()

        return binding.root
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
