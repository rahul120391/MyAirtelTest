package com.example.airteltest.search.datasource

import com.example.airteltest.network.retrofit.RetrofitServiceAnnotator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class RemoteSearchDataSource(private val retrofitServiceAnnotator: RetrofitServiceAnnotator) :
    ISearchDataSource {

    private var disposable: Disposable? = null

    override fun getAddressList(queryString: String?, callBack: ISearchDataSource.GetAddressData) {
        if (disposable?.isDisposed == false) {
            disposable?.dispose()
        }
        val flowAble = queryString?.let { retrofitServiceAnnotator.fetchAddress(it) }
        disposable = flowAble?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                it?.let { response ->
                    callBack.onAddressDataReceived(response)
                } ?: kotlin.run {
                    callBack.onError()
                }
            }, {
                println("error ${it.localizedMessage}")
                callBack.onError()
            })
    }
}