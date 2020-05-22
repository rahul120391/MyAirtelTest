package com.example.airteltest.search.repository

import com.example.airteltest.datamodel.AddressResponse
import com.example.airteltest.search.datasource.ISearchDataSource
import com.example.airteltest.search.datasource.LocalSearchDataSource
import com.example.airteltest.search.datasource.RemoteSearchDataSource

open class SearchRepository(private val localSearchDataSource: LocalSearchDataSource,private val remoteSearchDataSource: RemoteSearchDataSource):ISearchDataSource {

    override fun getAddressList(queryString: String?,callBack:ISearchDataSource.GetAddressData) {
            remoteSearchDataSource.getAddressList(queryString, object : ISearchDataSource.GetAddressData {
                override fun onAddressDataReceived(response: AddressResponse) {
                    callBack.onAddressDataReceived(response)
                }

                override fun onError() {
                    callBack.onError()
                }
            })
    }
}