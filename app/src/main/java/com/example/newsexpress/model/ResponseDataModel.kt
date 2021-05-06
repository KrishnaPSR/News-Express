package com.example.newsexpress.model

import com.google.gson.annotations.SerializedName

data class ResponseDataModel(

    @SerializedName("data")
    val data: ArrayList<SavedNewsData>
)
