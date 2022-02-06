package com.emanuel.zipcodesearch

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.emanuel.zipcodesearch.databinding.ActivityMainBinding
import com.emanuel.zipcodesearch.repository.AddressRepository
import com.emanuel.zipcodesearch.viewmodel.AddressViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: AddressViewModel by viewModels {
        AddressViewModel.ViewModelFactory(AddressRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
        setObservers()

    }

    private fun setListener() {
        binding.buttonSearch.setOnClickListener {
                binding.run {
                    progressCircular.visibility = View.VISIBLE
                    layoutShowInformation.visibility = View.INVISIBLE
                }

            val zip = binding.editInputZip.text.toString()
            if (validate(zip)) {
                viewModel.searchAddress(zip)
            }
        }
    }

    private fun validate(zip: String) : Boolean {
        return if (zip.length < 8) {
            binding.inputLayoutZip.error = "O cep precisa conter 8 caracteres"
            false
        } else {
            binding.inputLayoutZip.error = null
            true
        }
    }

    private fun setObservers() {
        viewModel.address.observe(this) { address ->
            binding.run {
                binding.progressCircular.visibility = View.INVISIBLE
                layoutShowInformation.visibility = View.VISIBLE
                textZip.text = address.zip
                textLogradouro.text = address.street
                textComplement.text = address.complement
                textDistrict.text = address.district
                textLocation.text = address.location
                textUf.text = address.uf
                textDdd.text = address.ddd
            }
        }

        viewModel.messageError.observe(this) { message ->
            binding.progressCircular.visibility = View.INVISIBLE
            binding.layoutShowInformation.visibility = View.INVISIBLE
            //Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
            val dialog = MaterialAlertDialogBuilder(this)
                .setTitle("Erro")
                .setMessage(message)
                .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
                .create()

            dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background_rounded)
            dialog.show()
        }

    }

}