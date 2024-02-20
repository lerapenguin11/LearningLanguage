package com.example.learninglanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.learninglanguage.databinding.ActivityRootBinding
import com.example.learninglanguage.navigation.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class RootActivity : AppCompatActivity(R.layout.activity_root) {
    private lateinit var binding: ActivityRootBinding
    private var currentNavController: LiveData<NavController>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navGraphIds = listOf(
            com.example.word_presentation.R.navigation.word_nav_graph,
            com.example.study_presentation.R.navigation.study_nav_graph,
            com.example.lists_presentation.R.navigation.list_nav_graph
        )

        val controller = binding.navView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.activity_root_fragment_nav_host,
            intent = this.intent
        )
        currentNavController = controller
    }
}