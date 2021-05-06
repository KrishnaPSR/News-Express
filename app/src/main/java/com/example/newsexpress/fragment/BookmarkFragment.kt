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
import com.example.newsexpress.adapter.SaveAdapter
import com.example.newsexpress.model.SavedNewsData
import kotlinx.android.synthetic.main.fragment_bookmark.*

/**
 * A simple [Fragment] subclass.
 * Use the [BookmarkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookmarkFragment : Fragment() {
    lateinit var viewmodel:  NewsExpressViewModel
    lateinit var adapter: SaveAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
    }


    private fun setupUI(){
        viewmodel = ViewModelProvider(this).get(NewsExpressViewModel(activity!!.application)::class.java)
        viewmodel.getNewsFromDatabase().observe(viewLifecycleOwner, Observer {articles ->
            rvNewsBookmark.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = SaveAdapter(activity as AppCompatActivity,articles as ArrayList<SavedNewsData>)
            }
        })
    }

}