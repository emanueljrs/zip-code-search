package com.emanuel.zipcodesearch.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.emanuel.zipcodesearch.models.Address
import com.emanuel.zipcodesearch.repository.AddressRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AddressViewModel(private val repository: AddressRepository) : ViewModel() {

    private val _addressRes = MutableLiveData<Address>()
    val address: LiveData<Address> = _addressRes

    private val _messageError = MutableLiveData<String>()
    val messageError: LiveData<String> = _messageError

    fun searchAddress(zip: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAddress(zip = zip)
                if (response.isSuccessful) {
                     _addressRes.postValue(response.body())
                } else {
                     _messageError.postValue(response.message())
                }

            } catch (e: Exception) {
                Log.i("zipsearch", "searchAddress: ${e.message}")
                _messageError.postValue("Verifique sua conexão de internet!")
            }
        }
    }

    class ViewModelFactory(private val repository: AddressRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddressViewModel::class.java)) {
                return AddressViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknow ViewModel class")
        }
    }
}