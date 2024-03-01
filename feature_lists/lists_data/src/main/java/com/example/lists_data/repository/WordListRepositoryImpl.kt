package com.example.lists_data.repository

import android.util.Log
import com.example.common_utils.common.ResultFirestore
import com.example.common_utils.utils.FIRE_STORE_DATABASE
import com.example.common_utils.utils.TOP_LIST_COLLECTION
import com.example.common_utils.utils.TOP_WORDS
import com.example.lists_data.mappers.WordListMapper
import com.example.lists_data.models.WordListModels
import com.example.lists_data.room.WordsDao
import com.example.lists_domain.entity.WordList
import com.example.lists_domain.repository.WordListRepository
import com.google.firebase.firestore.DocumentChange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class WordListRepositoryImpl(private val wordDao: WordsDao) : WordListRepository {
    private val mapper = WordListMapper()
    private val _wordListDoc = MutableStateFlow<List<WordListModels>>(emptyList())
    private val wordListDoc: StateFlow<List<WordListModels>> = _wordListDoc

    override suspend fun getWordList(documentId : String): ResultFirestore<List<WordList>> =
        withContext(Dispatchers.IO){
            eventChangeListenerWordList(documentId)
            delay(1000)
            try {
                return@withContext ResultFirestore.Success(mapper.toWordList(wordListDoc))
            } catch (e: Exception) {
                return@withContext ResultFirestore.Error(e)
            }
        }

    private fun eventChangeListenerWordList(documentId: String) {
        FIRE_STORE_DATABASE.collection(TOP_LIST_COLLECTION)
            .document(documentId).collection(TOP_WORDS)
            .addSnapshotListener { value, error ->
                if (error != null){
                    Log.d(TAG_FIRESTORE_ERROR, error.message.toString())
                    return@addSnapshotListener
                }
                val newList = mutableListOf<WordListModels>()
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        newList.add(dc.document.toObject(WordListModels::class.java))
                    }
                }
                _wordListDoc.value = newList
            }
    }

    override suspend fun getWordBookmarksList(): Flow<List<WordList>> {
        val savedBooksFlow = wordDao.getSavedWord()
        return savedBooksFlow.map { list ->
            list.map { element ->
                mapper.toWordListIsEntity(element)
            }
        }
    }

    override suspend fun addWord(word: WordList) {
        wordDao.saveWord(mapper.toWordEntity(word))
    }

    override suspend fun deleteWord(word: WordList) {
        wordDao.deleteWord(mapper.toWordEntity(word))
    }

    companion object{
        private const val TAG_FIRESTORE_ERROR = "Firestore Error: "
    }
}