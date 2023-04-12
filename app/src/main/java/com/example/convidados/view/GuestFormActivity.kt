package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
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
    }

    //trata o botão save
    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {}
    }
}
