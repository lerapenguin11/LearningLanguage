package com.example.common_utils.common

sealed class ResultFirestore<out R> {

    data class Success<out T>(val data: T) : ResultFirestore<T>()
    data class Error(val exception: Exception) : ResultFirestore<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}