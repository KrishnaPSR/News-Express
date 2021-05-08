package com.example.newsexpress.fragment

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    lateinit var category:String
    var keyword : String = ""

    //instance to get the connectivity method
    val mainActivity = MainActivity()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        category = this.requireArguments().getString("category").toString()
        return inflater.inflate(R.layout.fragment_home2, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search, menu)
        val search = menu.findItem(R.id.app_bar_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                keyword = query!!;
                setUpUI()
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                //SearchAdapter.filter.filter(newText)
                return false
            }
        })
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(mainActivity.isOnline(activity as AppCompatActivity)){
            setUpUI()
        }
        else{
            internetCheckText.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }

    }

    /**
     *function to set up UI of Home Fragment
     */
    private fun setUpUI(){
        progressBar.visibility = View.VISIBLE
        rvNewsList.visibility = View.GONE
        viewmodel = ViewModelProvider(this).get(NewsExpressViewModel(activity!!.application)::class.java)
        viewmodel.getNewsData(keyword,category)
        viewmodel.dataViewmodel.observe(viewLifecycleOwner, Observer { a ->
            rvNewsList.also {
                it.visibility = View.VISIBLE
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = GeneralAdapter(activity as AppCompatActivity,a.data)
            }
            progressBar.visibility =View.GONE
        })
    }
}