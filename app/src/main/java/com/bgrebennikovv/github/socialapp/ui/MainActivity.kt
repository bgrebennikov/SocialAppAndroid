package com.bgrebennikovv.github.socialapp.ui

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import com.bgrebennikovv.github.socialapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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