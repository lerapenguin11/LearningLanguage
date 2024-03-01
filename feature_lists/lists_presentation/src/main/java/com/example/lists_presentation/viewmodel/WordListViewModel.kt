package com.example.lists_presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_utils.common.ResultFirestore
import com.example.lists_domain.entity.WordList
import com.example.lists_domain.usecase.GetWordListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordListViewModel@Inject constructor
    (private val getWordListUseCase: GetWordListUseCase)
    : ViewModel()
{
    private val _wordList = MutableLiveData<List<WordList>?>()
    val wordList : LiveData<List<WordList>?> get() = _wordList

    private val _errorWordList = MutableLiveData<String>()
    val errorWordList : LiveData<String> = _errorWordList


    fun getWordList(documentId : String) {
        viewModelScope.launch {
            when (val wordListResult = getWordListUseCase.invoke(documentId)) {
                is ResultFirestore.Success -> {
                    _wordList.value = wordListResult.data
                }
                is ResultFirestore.Error -> {
                    _errorWordList.postValue(wordListResult.exception.message)
                }
            }
        }
    }
}