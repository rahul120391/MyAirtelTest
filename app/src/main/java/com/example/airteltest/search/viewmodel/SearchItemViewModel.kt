package com.example.airteltest.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.airteltest.datamodel.Address

class SearchItemViewModel(address: Address) {


    val city = MutableLiveData<String>()
    val addressData = MutableLiveData<String>()

    init {
        city.value=address.city
        addressData.value=address.addressString
    }
}