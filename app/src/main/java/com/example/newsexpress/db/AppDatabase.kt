package com.example.newsexpress.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsexpress.model.SavedNewsData

@Database(entities = [SavedNewsData::class] , version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun SavedNewsDao(): SavedNewsDao
}