package com.bgrebennikovv.github.socialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.bgrebennikovv.github.socialapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.root_fragment_container) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return  navHostFragment.navController.navigateUp() ||super.onSupportNavigateUp()
    }

}