package com.example.newsexpress.db

import android.content.Context
import androidx.room.Room

class AppDatabaseBuilder {

    //to create Instance
    private var INSTANCE: AppDatabase? = null
    fun getInstance(context: Context): AppDatabase {
        /*
        to check the existence of database
         */
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = createRoomDb(context)
            }
        }
        return INSTANCE!!
    }
    /*
    function to Create Database
     */
    private fun createRoomDb(context: Context): AppDatabase? {
        return Room.databaseBuilder(context, AppDatabase::class.java, "Saved News Entity")
            .fallbackToDestructiveMigration()
            .build()
    }
}