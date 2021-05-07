package com.example.newsexpress.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsexpress.MainActivity
import com.example.newsexpress.NewsExpressViewModel
import com.example.newsexpress.R
import com.example.newsexpress.adapter.GeneralAdapter
import kotlinx.android.synthetic.main.fragment_home2.*



class HomeFragment : Fragment() {

    lateinit var viewmodel: NewsExpressViewModel
    //Optional category to get news by Category
    lateinit var category:String
    //Making instance to get the connectivity method
    val mainActivity = MainActivity()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        category = this.requireArguments().getString("category").toString()
        return inflater.inflate(R.layout.fragment_home2, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (activity as AppCompatActivity).supportActionBar?.hide()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(mainActivity.isOnline(activity as AppCompatActivity)){
            setUi()
        }
        else{
            internetCheckText.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }

    }

    /**
     *To set up the UI of HomePage Fragment
     */
    fun setUi(){
        progressBar.visibility = View.VISIBLE
        viewmodel = ViewModelProvider(this).get(NewsExpressViewModel(activity!!.application)::class.java)
        viewmodel.getNewsData(null,category)
        viewmodel.dataViewmodel.observe(viewLifecycleOwner, Observer { a ->
            rvNewsList.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = GeneralAdapter(activity as AppCompatActivity,a.data)
            }
        })
    }
}