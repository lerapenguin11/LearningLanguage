package com.example.learninglanguage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.common_utils.utils.initFirebase
import com.example.learninglanguage.databinding.ActivityRootBinding
import com.example.learninglanguage.navigation.setupWithNavController
import com.example.lists_presentation.ui.WordsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : AppCompatActivity(R.layout.activity_root){
    private lateinit var binding: ActivityRootBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavController()

        if (currentNavController != null){
            showAndHideBottomNavigationBar()
        }
    }

    private fun showBottomNavigationBar(){
        binding.navView.visibility = View.VISIBLE
    }

    private fun hideBottomNavigationBar(){
        binding.navView.visibility = View.GONE
    }

    private fun showAndHideBottomNavigationBar() {
        currentNavController!!.observeForever {navController ->
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    com.example.word_presentation.R.id.wordsFragment -> showBottomNavigationBar()
                    com.example.study_presentation.R.id.studyFragment -> showBottomNavigationBar()
                    com.example.lists_presentation.R.id.listsFragment -> showBottomNavigationBar()
                    com.example.lists_presentation.R.id.wordsListFragment -> hideBottomNavigationBar()
                    com.example.detailed_presentation.R.id.detailedFragment -> hideBottomNavigationBar()
                    else -> showBottomNavigationBar()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        initFirebase()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun setNavController() {
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