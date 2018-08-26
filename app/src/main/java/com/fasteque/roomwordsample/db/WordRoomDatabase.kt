package com.fasteque.roomwordsample.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fasteque.roomwordsample.db.dao.WordDao
import com.fasteque.roomwordsample.db.entity.Word


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
                                // Wipes and rebuilds instead of migrating if no Migration object.
                                // Migration is not part of this example.
                                .fallbackToDestructiveMigration()
                                .addCallback(roomDatabaseCallback)
                                .build()

                    }
                }
            }
            return this.INSTANCE!!
        }

        /**
         * Override the onOpen method to populate the database.
         * For this sample, we clear the database every time it is created or opened.
         */
        private val roomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts, comment out the following line.
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }
    }

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private class PopulateDbAsync internal constructor(db: WordRoomDatabase) : AsyncTask<Void, Void, Void>() {

        private val dao: WordDao = db.wordDao()

        override fun doInBackground(vararg params: Void): Void? {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            dao.deleteAll()

            dao.insert(Word("Hello"))
            dao.insert(Word("World"))
            return null
        }
    }
}