package com.example.lists_presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_utils.common.ResultFirestore
import com.example.lists_domain.entity.WordList
import com.example.lists_domain.usecase.AddWordUseCase
import com.example.lists_domain.usecase.DeleteWordUseCase
import com.example.lists_domain.usecase.GetWordBookmarksListUseCase
import com.example.lists_domain.usecase.GetWordListUseCase
import com.example.lists_presentation.entity.WordsListWithStatus
import com.example.lists_presentation.mapper.WordWithStatusMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordListViewModel@Inject constructor(
    private val getWordListUseCase: GetWordListUseCase,
    private val getWordBookmarksListUseCase: GetWordBookmarksListUseCase,
    private val addWordUseCase: AddWordUseCase,
    private val deleteWordUseCase: DeleteWordUseCase)
    : ViewModel()
{
    private val _wordList = MutableLiveData<List<WordsListWithStatus>?>()
    val wordList : LiveData<List<WordsListWithStatus>?> get() = _wordList

    private val _errorWordList = MutableLiveData<String>()
    val errorWordList : LiveData<String> = _errorWordList

    private val _remoteWords = arrayListOf<WordList>()

    private val mapper: WordWithStatusMapper = WordWithStatusMapper()

    fun getWordList(documentId : String) {
        viewModelScope.launch {
            when (val wordListResult = getWordListUseCase.invoke(documentId)) {
                is ResultFirestore.Success -> {
                    _remoteWords.clear()
                    _remoteWords.addAll(wordListResult.data)

                    val wordsBookmarksFlow = getWordBookmarksListUseCase.invoke()
                    wordsBookmarksFlow.collect { bookmarks ->
                        _wordList.value = mapper.fromWordListToWordWithStatus(_remoteWords, bookmarks)
                    }
                }
                is ResultFirestore.Error -> {
                    _errorWordList.postValue(wordListResult.exception.message)
                }
            }
        }
    }

    fun bookmark(word : WordsListWithStatus) {
        viewModelScope.launch {
            addWordUseCase.invoke(mapper.fromWordWithStatusToWordList(word))
        }
    }

    fun unbookmark(word : WordsListWithStatus) {
        viewModelScope.launch {
            deleteWordUseCase.invoke(mapper.fromWordWithStatusToWordList(word))
        }
    }
}