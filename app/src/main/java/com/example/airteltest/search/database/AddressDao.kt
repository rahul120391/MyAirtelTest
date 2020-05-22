package com.example.airteltest.search.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface AddressDao {

    @Insert
    fun insertDataIntoDatabase(address: Address):Long
}