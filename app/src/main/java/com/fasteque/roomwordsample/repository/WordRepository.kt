package com.fasteque.roomwordsample.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.fasteque.roomwordsample.db.WordRoomDatabase
import com.fasteque.roomwordsample.db.dao.WordDao
import com.fasteque.roomwordsample.db.entity.Word


/**
 * Created by taaalda5 on 26.08.18.
 * Project: RoomWordSample
 */
class WordRepository internal constructor(application: Application) {

    private val wordDao: WordDao
    internal val allWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        wordDao = db.wordDao()
        allWords = wordDao.getAllWords()
    }

    fun insert(word: Word) {
        InsertAsyncTask(wordDao).execute(word)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) : AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}