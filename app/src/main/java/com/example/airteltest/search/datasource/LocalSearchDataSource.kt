package com.example.airteltest.search.datasource

import com.example.airteltest.database.MyDatabase

open class LocalSearchDataSource(myDatabase: MyDatabase):ISearchDataSource {

    override fun getAddressList(queryString: String?, callBack: ISearchDataSource.GetAddressData) {

    }
}