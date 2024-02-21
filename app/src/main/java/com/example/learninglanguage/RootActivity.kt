package com.example.learninglanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.ListFragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.learninglanguage.databinding.ActivityRootBinding
import com.example.learninglanguage.navigation.setupWithNavController
import com.example.study_presentation.StudyFragment
import com.example.word_presentation.WordsFragment

class RootActivity : AppCompatActivity(R.layout.activity_root) {
    private lateinit var binding: ActivityRootBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setTitleTextAppearance(this, R.style.TopAppBarTextAppearance)

        setNavController()
        if (currentNavController != null){
            setTitleToolbar()
        }
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

    private fun setTitleToolbar() {
        currentNavController!!.observeForever {navController ->
            navController.addOnDestinationChangedListener { _, destination, _ ->
                val fragment = when (destination.id) {
                    com.example.word_presentation.R.id.wordsFragment -> R.string.text_words
                    com.example.study_presentation.R.id.studyFragment -> R.string.text_learning
                    com.example.lists_presentation.R.id.listsFragment -> R.string.text_lists
                    else -> R.string.app_name
                }
                binding.topAppBar.setTitle(fragment)
                binding.topAppBar
            }
        }
    }
}