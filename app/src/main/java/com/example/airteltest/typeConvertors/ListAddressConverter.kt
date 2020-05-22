package com.example.airteltest.typeConvertors

import androidx.room.TypeConverter
import com.example.airteltest.datamodel.Address
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ListAddressConverter {

    private val gSon = Gson()
    @TypeConverter
    fun stringToSomeObject(data: String?): List<Address> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Address>>() {

        }.type

        return gSon.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectToString(listObject: List<Address>): String {
        return gSon.toJson(listObject)
    }
}