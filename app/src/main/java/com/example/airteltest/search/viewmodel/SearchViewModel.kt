package com.example.airteltest.search.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.airteltest.base.BaseViewModel
import com.example.airteltest.datamodel.Address
import com.example.airteltest.network.baseusecase.UseCase
import com.example.airteltest.network.baseusecase.UseCaseHandler
import com.example.airteltest.search.usecase.SearchUseCase
import com.example.airteltest.utils.Utility


open class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    useCaseHandler: UseCaseHandler,
    private val utility: Utility
) : BaseViewModel(useCaseHandler) {

    val addressListResponseLiveData = MutableLiveData<List<Address>>()
    val searchText = ObservableField<String>()
    var progressBarVisibility = ObservableField<Int>()
    var cancelButtonVisibility = ObservableField<Int>()
    var recyclerViewVisibility = ObservableField<Int>()
    var noResultFoundVisibility = ObservableField<Int>()
    var searchTextValue = ""

    init {
        setAllViewsGone()
    }

    fun getAddressList(queryString: String?) {
        if (utility.checkNetworkConnectivity()) {
            if (progressBarVisibility.get() != View.VISIBLE) {
                progressBarVisibility.set(View.VISIBLE)
            }
            val requestValues = SearchUseCase.RequestValues(queryString)
            useCaseHandler.execute(
                searchUseCase,
                requestValues,
                object : UseCase.UseCaseCallback<SearchUseCase.ResponseValue> {
                    override fun onSuccess(response: SearchUseCase.ResponseValue) {
                        if (response.addressResponse.data?.addressList?.isNotEmpty()==true) {
                            if (searchTextValue.isNotBlank()) {
                                addressListResponseLiveData.value =
                                    response.addressResponse.data.addressList
                                showHideViewsOnSuccess(false)
                            } else {
                                addressListResponseLiveData.value = emptyList()
                                setAllViewsGone()
                            }

                        } else {
                            showHideViewsOnSuccess(true)
                        }
                    }

                    override fun onError() {
                        hideViewsOnError()
                        utility.showErrorMessage()
                    }
                }
            )
        } else {
            hideViewsOnError()
            utility.showNoInternetError()
        }
    }

    private fun hideViewsOnError() {
        noResultFoundVisibility.set(View.GONE)
        recyclerViewVisibility.set(View.GONE)
        progressBarVisibility.set(View.GONE)
    }

    private fun showHideViewsOnSuccess(isEmpty: Boolean) {
        if (isEmpty) {
            noResultFoundVisibility.set(View.VISIBLE)
            recyclerViewVisibility.set(View.GONE)
        } else {
            noResultFoundVisibility.set(View.GONE)
            recyclerViewVisibility.set(View.VISIBLE)
        }
        progressBarVisibility.set(View.GONE)
    }

    fun getSearchTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Do nothing.
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                searchTextValue = s.trim().toString()
                searchText.set(searchTextValue)
                if (s.trim().isNotBlank()) {
                    cancelButtonVisibility.set(View.VISIBLE)
                    recyclerViewVisibility.set(View.GONE)
                    noResultFoundVisibility.set(View.GONE)
                    getAddressList(s.toString())
                } else {
                    setAllViewsGone()
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        }
    }


    companion object {
        @BindingAdapter("textChangedListener")
        @JvmStatic
        fun bindTextWatcher(editText: EditText, textWatcher: TextWatcher?) {
            editText.addTextChangedListener(textWatcher)
        }
    }

    private fun setAllViewsGone() {
        recyclerViewVisibility.set(View.GONE)
        progressBarVisibility.set(View.GONE)
        cancelButtonVisibility.set(View.GONE)
        noResultFoundVisibility.set(View.GONE)
    }

    fun onCancelImageClick() {
        searchText.set(" ")
        addressListResponseLiveData.value = emptyList()
    }

}