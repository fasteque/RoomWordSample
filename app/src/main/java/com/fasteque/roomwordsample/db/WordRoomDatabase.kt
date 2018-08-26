package com.fasteque.roomwordsample.db

import android.content.Context
import androidx.room.Room
import com.fasteque.roomwordsample.db.dao.WordDao
import androidx.room.RoomDatabase
import com.fasteque.roomwordsample.db.entity.Word
import androidx.room.Database



/**
 * Created by taaalda5 on 26.08.18.
 * Project: RoomWordSample
 */
@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        private var INSTANCE: WordRoomDatabase? = null


        internal fun getDatabase(context: Context): WordRoomDatabase {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                WordRoomDatabase::class.java, "word_database")
                                .build()

                    }
                }
            }
            return this.INSTANCE!!
        }
    }

}