package com.bgrebennikovv.github.socialapp.ui

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import com.bgrebennikovv.github.socialapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private var _binding : ActivityMainBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return  navHostFragment.navController.navigateUp() ||super.onSupportNavigateUp()
    }

    override fun finishAfterTransition() {
        super.finishAfterTransition()
    }


    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        return super.getOnBackInvokedDispatcher()
    }

}