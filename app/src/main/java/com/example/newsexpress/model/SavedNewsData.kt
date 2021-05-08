package com.example.newsexpress.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "SavedNewsData")
data class SavedNewsData(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @SerializedName("author")
    val author:String?,
    @SerializedName("title")
    val title:String?,
    @SerializedName("url")
    val url:String?,
    @SerializedName("image")
    val image:String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("published_at")
    val published_at:String
):Serializable