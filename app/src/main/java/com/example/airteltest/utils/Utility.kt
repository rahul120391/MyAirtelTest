package com.example.airteltest.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.airteltest.R
import com.google.android.material.snackbar.Snackbar

open class Utility private constructor() {


    companion object {
        var context: Context? = null
        private val instance = Utility()

        @Synchronized
        fun getInstance(context: Context): Utility {
            this.context = context
            return instance
        }
    }

    fun checkNetworkConnectivity():Boolean=
        context?.let { NetworkConnectivityStatus.getInstance(it).isNetworkAvailable() }?:false

    fun showErrorMessage():String= context?.getString(R.string.errorWhileFetchingData)?:""

    fun showNoInternetError(){
        context?.getString(R.string.noInternetAvailable)?.let {
            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        }
    }
}