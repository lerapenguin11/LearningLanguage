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
class RootActivity : AppCompatActivity(R.layout.activity_root),
    WordsListFragment.OnToolbarTitleChangeListener{
    private lateinit var binding: ActivityRootBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setTitleTextAppearance(this, R.style.TopAppBarTextAppearance)

        setNavController()

        val sharedPref = getSharedPreferences(SHAR_PREF_NAME_NAV, Context.MODE_PRIVATE)
        val currentFragmentId = sharedPref.getInt(KEY_SHAR_PREF_FRAGMENT_ID,
            com.example.word_presentation.R.id.wordsFragment)

        currentNavController?.value?.let { navController ->
            navController.navigate(currentFragmentId)
        }

        if (currentNavController != null){
            setTitleToolbar()
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
                    else -> showBottomNavigationBar()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        initFirebase()
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
                    com.example.word_presentation.R.id.wordsFragment -> "Слова"
                    com.example.study_presentation.R.id.studyFragment -> "Изучение"
                    com.example.lists_presentation.R.id.listsFragment -> "Списки"
                    else -> "Top words"
                }
                binding.topAppBar.title = fragment

                val sharedPref = getSharedPreferences(SHAR_PREF_NAME_NAV, Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putInt(KEY_SHAR_PREF_FRAGMENT_ID, destination.id)
                    apply()
                }
            }
        }
    }

    override fun onTitleChanged(title: String) {
        binding.topAppBar.title = title
        binding.topAppBar.setTitleTextAppearance(this, R.style.TopAppBarTextAppearance)
    }

    companion object{
        const val SHAR_PREF_NAME_NAV = "navigation"
        const val KEY_SHAR_PREF_FRAGMENT_ID = "currentFragment"
    }
}