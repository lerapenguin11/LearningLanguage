package com.example.lists_data.mappers

import com.example.lists_data.models.TopListModels
import com.example.lists_data.models.WordListModels
import com.example.lists_domain.entity.TopList
import com.example.lists_domain.entity.WordList
import kotlinx.coroutines.flow.StateFlow

class TopListMapper
{
    fun toTopList(topList: StateFlow<List<TopListModels>>): List<TopList>{
        val list = arrayListOf<TopList>()
        for (i in topList.value){
            val id = i.id
            val title = i.title
            val new_words = i.new_words

            val model = TopList(
                id = id,
                title = title,
                new_words = new_words
            )
            list.add(model)
        }
        return list
    }

    fun toWordList(wordList: StateFlow<List<WordListModels>>) : List<WordList>{
        val list = arrayListOf<WordList>()
        for (i in wordList.value){
            val model = WordList(
                id = i.id,
                word = i.word,
                translation = i.translation
            )
            list.add(model)
        }
        return list
    }
}