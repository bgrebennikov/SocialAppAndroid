package com.bgrebennikovv.github.socialapp

import android.os.Bundle
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

}