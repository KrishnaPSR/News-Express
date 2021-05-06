package com.example.newsexpress.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsexpress.model.SavedNewsData

@Dao
interface SavedNewsDao {

    @Query("Select * from SavedNewsData")
    fun getAllNews(): LiveData<List<SavedNewsData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(SavedNewsData: SavedNewsData):Long

    @Delete
    fun deleteNews(SavedNewsData: SavedNewsData)
}