package com.example.lists_data.mappers

import com.example.lists_data.models.TopListModels
import com.example.lists_domain.entity.TopList

class TopListMapper
{
    fun toTopList(topList: List<TopListModels>): List<TopList>{
        val list = arrayListOf<TopList>()
        for (i in topList){
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
}