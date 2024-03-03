package com.example.lists_presentation.mapper

import com.example.lists_domain.entity.WordList
import com.example.lists_presentation.entity.BookmarkStatus
import com.example.lists_presentation.entity.WordsListWithStatus

class WordWithStatusMapper
{
    fun fromWordWithStatusToWordList(wordWithStatus : WordsListWithStatus) : WordList {
        return WordList(
            id = wordWithStatus.id,
            word = wordWithStatus.word,
            translation = wordWithStatus.translation,
            transcription = wordWithStatus.transcription,
            notes = wordWithStatus.notes,
            progress = wordWithStatus.progress
        )
    }

    fun fromWordListToWordWithStatus(
        remoteWords : List<WordList>,
        bookmarks : List<WordList>
    ): List<WordsListWithStatus> {
        val commonElements = remoteWords.filter { it.id in bookmarks.map { bookmark -> bookmark.id } }
        val wordsWithStatus = arrayListOf<WordsListWithStatus>()
        for (wordWithStatus in remoteWords) {
            if (wordWithStatus in commonElements) {
                wordsWithStatus.add(
                    WordsListWithStatus(
                        id = wordWithStatus.id,
                        word = wordWithStatus.word,
                        translation = wordWithStatus.translation,
                        transcription = wordWithStatus.transcription,
                        notes = wordWithStatus.notes,
                        progress = wordWithStatus.progress,
                        status = BookmarkStatus.BOOKMARKED
                    )
                )
            } else {
                wordsWithStatus.add(
                    WordsListWithStatus(
                        id = wordWithStatus.id,
                        word = wordWithStatus.word,
                        translation = wordWithStatus.translation,
                        transcription = wordWithStatus.transcription,
                        notes = wordWithStatus.notes,
                        progress = wordWithStatus.progress,
                        status = BookmarkStatus.UNBOOKMARKED
                    )
                )
            }
        }
        return wordsWithStatus.sortedBy { it.id }
    }
}