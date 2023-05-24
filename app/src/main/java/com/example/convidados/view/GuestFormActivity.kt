package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    //na inserção de um convidado na lista o guestId é 0(zero) ... já na atualização o guestId é diferente de 0(zero) --> fun loadData() logo ali embaixo
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //instancia o binding e infla o layout
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instancia a viewModel
        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        //deixa o status 'presente' marcado por padrão
        binding.radioPresent.isChecked = true

        binding.buttonSave.setOnClickListener(this)

        observe()

        loadData()
    }

    //trata o botão save, o campo do nome do convidado e a presença do convidado
    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            //vincula os dados com a GuestFormViewModel | instancia a GuestModel colocada na GuestFormViewModel
            val model = GuestModel(guestId, name, presence) //var 'guestId' é para inserção ou atualização de um convidado...
            viewModel.save(model) //salva a insert(inserção de um convidado) ou a update(atualização de um convidado) ... todas as funs na GuestFormViewModel

            finish() //fecha a Activity após a inserção ou atualização de um convidado
        }
    }

    //observa os dados de um convidado --> GuestFormViewModel
    private fun observe() {
        viewModel.guest.observe(this, Observer { //traz o valor\a informação de um convidado
            //mostra e diz ao usuário sobre as informações do elemento clicado
            binding.editName.setText(it.name)
            if (it.presence) { //se a presença estiver ok...
                binding.radioPresent.isChecked = true
            } else { //senão...
                binding.radioAbsent.isChecked = true
            }
        })
    }

    //verifica se há dados ou não - carrega os dados de somente 1 convidado
    private fun loadData() {
        val bundle = intent.extras //busca/pega a intent da AllGuestsFragment com o pacote de informações
        if (bundle != null) { //prevenção para o caso da intent voltar vazia, sem valores/informações
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID) //pega o id do convidado... o guestId com valor atualizado, sendo diferente de 0(zero) ...
            viewModel.get(guestId) //se a intent voltar com valores, o convidado é carregado --> fun get criada em GuestRepository
        }
    }
}
