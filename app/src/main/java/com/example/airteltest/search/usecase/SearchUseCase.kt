package com.example.airteltest.search.usecase

import com.example.airteltest.datamodel.AddressResponse
import com.example.airteltest.network.baseusecase.UseCase
import com.example.airteltest.search.datasource.ISearchDataSource
import com.example.airteltest.search.repository.SearchRepository

open class SearchUseCase(private val searchRepository: SearchRepository):UseCase<SearchUseCase.RequestValues,SearchUseCase.ResponseValue>(){


    override fun executeUseCase(requestValues: RequestValues?) {

        requestValues?.let {
            searchRepository.getAddressList(it.queryString, object : ISearchDataSource.GetAddressData {
                override fun onAddressDataReceived(response: AddressResponse) {
                    useCaseCallback?.onSuccess(ResponseValue(response))
                }

                override fun onError() {
                     useCaseCallback?.onError()
                }
            })
        }

    }

    class RequestValues(val queryString: String?): UseCase.RequestValues
    class ResponseValue(val addressResponse:AddressResponse) : UseCase.ResponseValue
}