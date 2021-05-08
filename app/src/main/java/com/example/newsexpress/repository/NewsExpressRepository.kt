package com.example.newsexpress.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsexpress.api.ApiClient
import com.example.newsexpress.api.constant.Companion.apiKey
import com.example.newsexpress.db.AppDatabase
import com.example.newsexpress.db.AppDatabaseBuilder
import com.example.newsexpress.model.ResponseDataModel
import com.example.newsexpress.model.SavedNewsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class NewsExpressRepository {

    val data: MutableLiveData<ResponseDataModel> = MutableLiveData()

    fun getNews(keyword: String?, category: String) {
        val news = ApiClient.getClient.getNewsData(apiKey, category, "in", "en", 100, keyword)
        news.enqueue(object : Callback<ResponseDataModel> {
            override fun onResponse(
                call: Call<ResponseDataModel>,
                response: Response<ResponseDataModel>
            ) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                Log.e("Homepage", "Some Error Occured")
            }
        })
    }
    fun getNewsDataFromDatabase(context: Context): LiveData<List<SavedNewsData>> {
        val database = AppDatabaseBuilder().getInstance(context)
        return database.SavedNewsDao().getAllNews()
    }

    fun deleteNewsArticle(context: Context, data: SavedNewsData) {
        val database = AppDatabaseBuilder().getInstance(context)
        Executors.newSingleThreadExecutor().execute {
            database.SavedNewsDao().deleteNews(data)
        }
    }
    fun insertData(context: Context, data: SavedNewsData) {
        val database = AppDatabaseBuilder().getInstance(context)
        Executors.newSingleThreadExecutor().execute {
            database.SavedNewsDao().insert(data)
        }
    }
}

