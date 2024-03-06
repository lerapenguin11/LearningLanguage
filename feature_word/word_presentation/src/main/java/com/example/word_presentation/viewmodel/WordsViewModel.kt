package com.example.word_presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.word_domain.model.WordsList
import com.example.word_domain.usecase.DeleteWordUseCase
import com.example.word_domain.usecase.GetWordsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordsViewModel @Inject constructor(
    private val getWordsListUseCase : GetWordsListUseCase,
    private val deleteWordUseCase : DeleteWordUseCase
)
    : ViewModel()
{
    private val _wordList = MutableLiveData<List<WordsList>?>()
    val wordList : LiveData<List<WordsList>?> get() = _wordList

    init {
        getWordList()
    }

    fun deleteWord(word : WordsList) {
        viewModelScope.launch {
            deleteWordUseCase.invoke(word)
        }
    }

    fun getWordList() {
        viewModelScope.launch {
            getWordsListUseCase.invoke()
            .collect { bookmarks ->
                _wordList.value = bookmarks
            }
        }
    }
}