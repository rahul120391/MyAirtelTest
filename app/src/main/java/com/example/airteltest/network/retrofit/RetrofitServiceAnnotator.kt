package com.example.airteltest.network.retrofit


import com.example.airteltest.constants.Constants.QUERY_STRING
import com.example.airteltest.datamodel.AddressResponse
import com.example.airteltest.network.urls.RequestUrl.ADDRESS_URL
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceAnnotator {

    @GET(ADDRESS_URL)
    fun fetchAddress(@Query(QUERY_STRING) queryString:String):Flowable<AddressResponse>
}