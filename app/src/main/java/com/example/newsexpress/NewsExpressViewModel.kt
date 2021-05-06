package com.example.newsexpress

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newsexpress.model.ResponseDataModel
import com.example.newsexpress.model.SavedNewsData
import com.example.newsexpress.repository.NewsExpressRepository

class NewsExpressViewModel(application: Application):AndroidViewModel(application) {
    //val repo:NewsExpressRepository = NewsExpressRepository()
    //getting context in viewmodel
    val repository: NewsExpressRepository =NewsExpressRepository()
    private val context = getApplication<Application>().applicationContext
    val dataViewmodel: LiveData<ResponseDataModel> = repository.data

    /**
     * TO call the data from Api from Repo
     */
    fun getNewsData(keyword:String?,category:String){
        repository.getNews(keyword,category)
    }

    /**
     * TO insert the data in database using Repo
     */
    fun insertNews(data:SavedNewsData){
        repository.insertData(context,data)
    }

    /**
     * To delete the data from Database
     */
    fun deleteNews(data: SavedNewsData){
        repository.deleteNewsArticle(context,data)
    }

    /**
     * to get the data from database
     */
    fun getNewsFromDatabase(): LiveData<List<SavedNewsData>> {
        return repository.getNewsDataFromDatabase(context)
    }
}
