package com.example.newsexpress

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.newsexpress.fragment.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
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
        changeFragment(HomeFragment())


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.general -> {
                setToolbarTitle("Home News")
                changeFragment(HomeFragment())
            }
            R.id.entertainment -> {
                setToolbarTitle("Entertainment News")
                changeFragment(EntertainmentFragment())
            }
            R.id.healthNews ->{
                setToolbarTitle("Health News")
                changeFragment(HealthNewsFragment())
            }
            R.id.scienceNews ->{
                setToolbarTitle("Science News")
                changeFragment(ScienceFragment())
            }
            R.id.technoNews ->{
                setToolbarTitle("Technical News")
                changeFragment(TechnicalNewsFragment())
            }
            R.id.sportsNews ->{
                setToolbarTitle("Sports News")
                changeFragment(SportNewsFragment())
            }
            R.id.businessToday ->{
                setToolbarTitle("Business Today")
                changeFragment(BusinessNewsFragment())
            }
            R.id.bookmark ->{
                setToolbarTitle("Saved News")
                changeFragment(BookmarkFragment())
            }
        }
        return true
    }

    private fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun changeFragment(frag: Fragment) {
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container,frag).commit()

    }

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
