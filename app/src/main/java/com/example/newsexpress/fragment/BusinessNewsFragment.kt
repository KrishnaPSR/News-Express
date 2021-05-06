package com.example.newsexpress.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsexpress.NewsExpressViewModel
import com.example.newsexpress.R
import com.example.newsexpress.adapter.GeneralAdapter
import com.example.newsexpress.adapter.SaveAdapter
import com.example.newsexpress.model.SavedNewsData
import kotlinx.android.synthetic.main.fragment_bookmark.*
import kotlinx.android.synthetic.main.fragment_business_news.*

/**
 * A simple [Fragment] subclass.
 * Use the [BusinessNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessNewsFragment : Fragment() {
    lateinit var viewModel: NewsExpressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_news, container, false)
    }



}