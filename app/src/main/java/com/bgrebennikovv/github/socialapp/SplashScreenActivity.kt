package com.bgrebennikovv.github.socialapp

import android.annotation.SuppressLint
import android.os.Bundle
import com.bgrebennikovv.github.socialapp.ui.BaseActivity
import com.bgrebennikovv.github.socialapp.ui.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateTo(MainActivity::class.java)
    }



}