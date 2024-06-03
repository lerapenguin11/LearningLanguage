package com.example.lists_data.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    /*@TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): ArrayList<String> = Gson().fromJson(value, Array<String>::class.java).toMutableList() as ArrayList<String>*/

    @TypeConverter
    fun fromTranslationList(value: List<Translation>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Translation>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toTranslationList(value: String): List<Translation> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Translation>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromNotesList(value: List<Notes>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Notes>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toNotesList(value: String): List<Notes> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Notes>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTransferStatusEntity(value: TransferStatusEntity): String {
        return value.name
    }

    @TypeConverter
    fun toTransferStatusEntity(value: String): TransferStatusEntity {
        return TransferStatusEntity.valueOf(value)
    }

    @TypeConverter
    fun fromStatusNotesEntity(value: StatusNotesEntity): String {
        return value.name
    }

    @TypeConverter
    fun toStatusNotesEntity(value: String): StatusNotesEntity {
        return StatusNotesEntity.valueOf(value)
    }
}