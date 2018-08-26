package com.fasteque.roomwordsample.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by taaalda5 on 26.08.18.
 * Project: RoomWordSample
 */

@Entity(tableName = "word_table")
class Word constructor(word: String) {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    var word: String = word
}