package com.example.lists_presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_utils.common.ResultFirestore
import com.example.lists_domain.entity.TopList
import com.example.lists_domain.usecase.GetTopListWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getTopListWordsUseCase: GetTopListWordsUseCase)
    : ViewModel()
{
    private val _topList = MutableLiveData<List<TopList>?>()
    val topList : LiveData<List<TopList>?> get() = _topList

    private val _errorTopList = MutableLiveData<String>()
    val errorTopList : LiveData<String> = _errorTopList

    init {
        getPatient()
    }

    private fun getPatient() {
        viewModelScope.launch {
            when (val topListResult = getTopListWordsUseCase.invoke()) {
                is ResultFirestore.Success -> {
                    _topList.value = topListResult.data
                }
                is ResultFirestore.Error -> {
                    _errorTopList.postValue(topListResult.exception.message)
                }
            }
        }
    }
}