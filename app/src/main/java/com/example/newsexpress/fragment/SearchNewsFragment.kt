package com.example.newsexpress.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import com.example.newsexpress.MainActivity
import com.example.newsexpress.NewsExpressViewModel
import com.example.newsexpress.R
import com.example.newsexpress.adapter.SearchAdapter
import java.util.Locale.filter

class SearchNewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_news, container, false)
    }



}