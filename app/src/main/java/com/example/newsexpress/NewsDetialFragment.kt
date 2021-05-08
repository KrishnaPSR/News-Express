package com.example.newsexpress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsexpress.model.SavedNewsData
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_news_detial.*

class NewsDetialFragment : Fragment() {
    lateinit var viewmodel: NewsExpressViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val article = args.article
        val articleId = this.arguments?.getInt("id")
        val author = this.arguments?.getString("author")
        val title = this.arguments?.getString("title")
        val url = this.arguments?.getString("url")
        val image = this.arguments?.getString("image")
        val description = this.arguments?.getString("description")
        val pulished_at = this.arguments?.getString("published_at")
        val article = this.arguments?.getSerializable("article")
        detailtextTitle.text = articleId.toString()
        detailTextDetail.text = description.toString()
        Glide.with(this)
            .load(image)
            .into(detailImageview)
        viewmodel = ViewModelProvider(this).get(NewsExpressViewModel::class.java)

        if( articleId != 0){
            addToBookmark.setOnClickListener {
                viewmodel.deleteNews(article as SavedNewsData)
                Snackbar.make(view,"Article Removed From Bookmark", Snackbar.LENGTH_SHORT).show()
            }
        }
        else {
            addToBookmark.setOnClickListener {
                viewmodel.insertNews(article as SavedNewsData)
                Snackbar.make(view, "Article added to Bookmark", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}