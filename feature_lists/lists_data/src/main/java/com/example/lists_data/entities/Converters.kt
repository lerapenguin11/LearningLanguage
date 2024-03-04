package com.example.lists_data.entities

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): ArrayList<String> = Gson().fromJson(value, Array<String>::class.java).toMutableList() as ArrayList<String>
}