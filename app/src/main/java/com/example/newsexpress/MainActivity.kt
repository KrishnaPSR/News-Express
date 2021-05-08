package com.example.newsexpress

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.newsexpress.fragment.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        nav_menu.setNavigationItemSelectedListener(this)
        setToolbarTitle("General")
        changeFragment(HomeFragment(),"")
        //BookmarkFragment(BookmarkFragment(),"")

        if (!isOnline(this)) {
            Toast.makeText(this,"No Internet", Toast.LENGTH_LONG).show()
            return
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.general -> {
                setToolbarTitle("Home News")
                changeFragment("")

            }
            R.id.entertainment -> {
                setToolbarTitle("Entertainment News")

                changeFragment("entertainment")
            }
            R.id.healthNews -> {
                setToolbarTitle("Health News")
                changeFragment("health")
            }
            R.id.scienceNews -> {
                setToolbarTitle("Science News")
                changeFragment("science")
            }
            R.id.technoNews -> {
                setToolbarTitle("Technical News")
                changeFragment("technology")
            }
            R.id.sportsNews -> {
                setToolbarTitle("Sports News")
                changeFragment("sports")
            }
            R.id.businessToday -> {
                setToolbarTitle("Business Today")
                changeFragment("business")
            }
            R.id.bookmark -> {
                setToolbarTitle("Saved News")
                changeFragment(BookmarkFragment(),"saved")
            }
        }
        return true
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                } else {
                    TODO("VERSION.SDK_INT < M")
                }
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false

    }
    private fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun changeFragment(category: String) {
        val fragment = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        val frag = HomeFragment()
        bundle.putString("category", category)
        frag.arguments = bundle
        fragment.replace(R.id.fragment_container, frag).commit()

    }
    private fun changeFragment(frag: Fragment,category: String) {
        val fragment = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("category", category)
        frag.arguments = bundle
        fragment.replace(R.id.fragment_container, frag).commit()

    }
    private fun BookmarkFragment(frag: Fragment,category: String) {
        val fragment = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        val frag= BookmarkFragment()
        bundle.putString("category", category)
        frag.arguments = bundle
        fragment.replace(R.id.fragment_container, frag).commit()

    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.search, menu)
//        val search = menu.findItem(R.id.app_bar_search)
//        val searchView = search.actionView as SearchView
//        searchView.queryHint = "Search"
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//            override fun onQueryTextChange(newText: String?): Boolean {
//                //SearchAdapter.filter.filter(newText)
//                return false
//            }
//        })
//        return true
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.main_menu,menu)
//        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchItem = menu?.findItem(R.id.search)
//        val searchView = searchItem?.actionView as SearchView
//
//        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                searchView.clearFocus()
//                searchView.setQuery("",false)
//                searchItem.collapseActionView()
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return false
//
//            }
//        })
//
//    }
}

