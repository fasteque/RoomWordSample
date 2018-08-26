package com.fasteque.roomwordsample.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.fasteque.roomwordsample.db.entity.Word
import com.fasteque.roomwordsample.repository.WordRepository


/**
 * Created by taaalda5 on 26.08.18.
 * Project: RoomWordSample
 */
class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository = WordRepository(application)

    internal val allWords: LiveData<List<Word>>

    init {
        allWords = repository.allWords
    }

    fun insert(word: Word) {
        repository.insert(word)
    }
}