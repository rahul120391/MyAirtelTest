package com.example.airteltest.search.datasource

import com.example.airteltest.datamodel.AddressResponse

interface ISearchDataSource {

     interface GetAddressData{
         fun onAddressDataReceived(response: AddressResponse)
         fun onError()
     }

    fun getAddressList(queryString: String?,callBack:GetAddressData)
}