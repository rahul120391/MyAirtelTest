package com.example.airteltest.search.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.airteltest.database.DatabaseConstants.ADDRESS_TABLE
import com.example.airteltest.database.DatabaseConstants.CITY
import com.example.airteltest.datamodel.Address

@Entity(tableName = ADDRESS_TABLE)
class Address {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = CITY)
    var list:List<Address>?=null
}