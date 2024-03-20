package com.example.detailed_presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detailed_domain.model.WordDetailed
import com.example.detailed_domain.usecase.DeleteWordDetailedUseCase
import com.example.detailed_domain.usecase.GetWordDetailedUseCase
import com.example.detailed_domain.usecase.UpdateWordDetailedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordDetailedViewModel @Inject constructor(
    private val getWordDetailedUseCase: GetWordDetailedUseCase,
    private val updateWordDetailedUseCase: UpdateWordDetailedUseCase,
    private val deleteWordDetailedUseCase: DeleteWordDetailedUseCase
) : ViewModel()
{
    private val _wordDetailed = MutableLiveData<WordDetailed?>()
    val wordDetailed : LiveData<WordDetailed?> get() = _wordDetailed

    fun deleteWord(word : WordDetailed) {
        viewModelScope.launch {
            deleteWordDetailedUseCase.invoke(word = word)
        }
    }

    fun updateWord(word : WordDetailed) {
        viewModelScope.launch {
            updateWordDetailedUseCase.invoke(word = word)
        }
    }

    fun getWordDetailedId(wordId : Int){
        viewModelScope.launch{
            _wordDetailed.value = getWordDetailedUseCase.invoke(wordId = wordId)
        }
    }
}