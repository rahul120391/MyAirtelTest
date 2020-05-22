package com.example.airteltest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.airteltest.database.DatabaseConstants.DATABASE_VERSION
import com.example.airteltest.search.database.Address
import com.example.airteltest.search.database.AddressDao
import com.example.airteltest.typeConvertors.ListAddressConverter

@Database(entities = [Address::class],exportSchema = false,version = DATABASE_VERSION)
@TypeConverters(ListAddressConverter::class)
abstract class MyDatabase : RoomDatabase(){
    abstract fun getAddressDao():AddressDao
}