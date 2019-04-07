package com.example.sunmoviedb

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.example.sunmoviedb.extensions.setToolbarStyleTitle
import com.example.sunmoviedb.extensions.setupWithNavController
import com.example.sunmoviedb.ui.account.AccountFragmentDirections
import com.example.sunmoviedb.ui.home.HomeFragmentDirections
import com.example.sunmoviedb.ui.library.LibraryFragmentDirections
import com.example.sunmoviedb.ui.watchedlist.WatchedListFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawerToggle: ActionBarDrawerToggle

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
        setupDrawer()
        setupNavigation()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply { setDisplayHomeAsUpEnabled(true) }
    }

    private fun setupDrawer() {
        drawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.open_drawer_label, R.string.close_drawer_label
        )
        drawerLayout.addDrawerListener(drawerToggle)
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.settings -> navigateToSetting()
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    private fun navigateToSetting() {
        val navController = currentNavController?.value ?: return
        when (currentNavController?.value?.graph?.id) {
            R.id.navigation_home -> {
                val action = HomeFragmentDirections.actionHomeToSetting()
                navController.navigate(action)
            }
            R.id.navigation_library -> {
                val action = LibraryFragmentDirections.actionLibraryToSetting()
                navController.navigate(action)
            }
            R.id.navigation_watched_list -> {
                val action = WatchedListFragmentDirections.actionWatchedListToSetting()
                navController.navigate(action)
            }
            R.id.navigation_account -> {
                val action = AccountFragmentDirections.actionAccountToSetting()
                navController.navigate(action)
            }
            else -> {
                val id = currentNavController?.value?.graph?.id
                Log.d("App_tag", "Not support graph with id: $id")
            }
        }
    }

    private fun setupNavigation() {
        val navGraphIds = listOf(
            R.navigation.navigation_home,
            R.navigation.navigation_library,
            R.navigation.navigation_watched_list,
            R.navigation.navigation_account
        )
        val controller = mainBottomNavigationView.setupWithNavController(
            navGraphIds, supportFragmentManager, R.id.container, intent
        )
        controller.observe(this, Observer {
            when (it.graph.startDestination) {
                R.id.home_fragment -> {
                    setToolbarStyleTitle(R.string.home_action_label)
                }
                R.id.library_fragment -> {
                    supportActionBar?.title = getString(R.string.library_label)
                }
                R.id.watched_list_fragment -> {
                    supportActionBar?.title = getString(R.string.watched_list_label)
                }
                R.id.account_fragment -> {
                    supportActionBar?.title = getString(R.string.account_label)
                }
            }
        })
        currentNavController = controller
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onStart() {
        super.onStart()
        navView.setCheckedItem(R.id.home)
    }
}
