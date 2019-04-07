package com.example.sunmoviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.app.TaskStackBuilder
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * Created in 4/8/19 by Sang
 * Description:
 */
class SettingActivity : AppCompatActivity() {

    private var currentDestination = R.id.global_setting_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setupToolbar()
        setupNavigation()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            NavUtils.navigateUpFromSameTask(this)
        }
    }

    private fun setupNavigation() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.settingHostFragment) as NavHostFragment
        val navController = navHost.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.global_setting_fragment -> {
                    currentDestination = R.id.global_setting_fragment
                    supportActionBar?.title = getString(R.string.setting_action_bar_title)
                }
                R.id.caption_setting_fragment -> {
                    currentDestination = R.id.caption_setting_fragment
                    supportActionBar?.title = getString(R.string.caption_setting_label)
                }
            }
        }
    }
}
