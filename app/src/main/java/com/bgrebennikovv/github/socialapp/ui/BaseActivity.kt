package com.bgrebennikovv.github.socialapp.ui

import android.content.Intent
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.ui.viewModels.AppSettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseActivity : AppCompatActivity() {

    val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.root_fragment_container) as NavHostFragment
    }

    private val settingsViewModel by viewModel<AppSettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        settingsViewModel.userIsAuthenticated().observe(this){ state ->
            val graphId : Int = if(state) R.navigation.authorized_nav else R.navigation.unauthorized_nav
            setRootGraph(graphId)
            return@observe
        }
        super.onCreate(savedInstanceState)
    }

    fun <T> navigateTo(activity: Class<T>){
        startActivity(
            Intent(this, activity)
        )
        finish()
    }

    private fun setRootGraph(graphId : Int){
        with(navHostFragment.navController){
            graph = navInflater.inflate(graphId)
        }
    }

    override fun finishAfterTransition() {
        super.finishAfterTransition()
    }


    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        return super.getOnBackInvokedDispatcher()
    }

}