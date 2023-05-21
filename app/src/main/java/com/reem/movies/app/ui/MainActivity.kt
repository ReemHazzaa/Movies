package com.reem.movies.app.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reem.movies.R
import com.reem.movies.app.base.baseUi.BaseActivity
import com.reem.movies.data.local.workManager.CachingWorker
import com.reem.movies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_top_rated, R.id.navigation_fav
            )
        )
        navView.setupWithNavController(navController)

        invalidateCacheEveryFourHours()
    }

    private fun invalidateCacheEveryFourHours() {
        val connectedConstraint = Constraints.Builder().run {
            NetworkType.CONNECTED
            build()
        }
        val periodicRequest = PeriodicWorkRequest.Builder(
            CachingWorker::class.java,
            4,
            TimeUnit.HOURS,
            1, TimeUnit.SECONDS
        )
            .setConstraints(connectedConstraint)
            .build()
        WorkManager
            .getInstance(this.applicationContext)
            .enqueue(periodicRequest)
    }
}