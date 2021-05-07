package com.example.newsexpress

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newsexpress.model.ResponseDataModel
import com.example.newsexpress.model.SavedNewsData
import com.example.newsexpress.repository.NewsExpressRepository

class NewsExpressViewModel(application: Application):AndroidViewModel(application) {
    val repository: NewsExpressRepository =NewsExpressRepository()
    private val context = getApplication<Application>().applicationContext
    val dataViewmodel: LiveData<ResponseDataModel> = repository.data

    fun getNewsData(keyword:String?,category:String){
        repository.getNews(keyword,category)
    }


    fun insertNews(data:SavedNewsData){
        repository.insertData(context,data)
    }


    fun deleteNews(data: SavedNewsData){
        repository.deleteNewsArticle(context,data)
    }

    fun getNewsFromDatabase(): LiveData<List<SavedNewsData>> {
        return repository.getNewsDataFromDatabase(context)
    }
}
