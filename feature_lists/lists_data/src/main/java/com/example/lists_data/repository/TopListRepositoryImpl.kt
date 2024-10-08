package com.example.lists_data.repository

import android.util.Log
import com.example.common_utils.common.ResultFirestore
import com.example.common_utils.utils.FIRE_STORE_DATABASE
import com.example.common_utils.utils.TOP_LIST_COLLECTION
import com.example.common_utils.utils.TOP_WORDS
import com.example.lists_data.mappers.TopListMapper
import com.example.lists_data.models.TopListModels
import com.example.lists_data.models.WordListModels
import com.example.lists_domain.entity.TopList
import com.example.lists_domain.entity.WordList
import com.example.lists_domain.repository.TopListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.firebase.firestore.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TopListRepositoryImpl : TopListRepository {
    private val mapper = TopListMapper()
    private val _topListDoc = MutableStateFlow<List<TopListModels>>(emptyList())
    private val topListDoc: StateFlow<List<TopListModels>> = _topListDoc

    override suspend fun getTopList(): ResultFirestore<List<TopList>> =
        withContext(Dispatchers.IO){
            eventChangeListenerTopList()
            delay(1000)
            try {
                return@withContext ResultFirestore.Success(mapper.toTopList(topListDoc))
            } catch (e: Exception) {
                return@withContext ResultFirestore.Error(e)
            }
        }

    private fun eventChangeListenerTopList(){
        FIRE_STORE_DATABASE.collection(TOP_LIST_COLLECTION)
            .addSnapshotListener { value, error ->
                if (error != null){
                    Log.d(TAG_FIRESTORE_ERROR, error.message.toString())
                    return@addSnapshotListener
                }
                val newList = mutableListOf<TopListModels>()
                for (dc: DocumentChange in value ?.documentChanges!!)
                {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        newList.add(dc.document.toObject(TopListModels::class.java))
                    }
                }
                _topListDoc.value = newList
            }
    }


    companion object{
        private const val TAG_FIRESTORE_ERROR = "Firestore Error: "
    }
}