package com.bgrebennikovv.github.socialapp

import android.annotation.SuppressLint
import android.os.Bundle

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateTo(MainActivity::class.java)
    }



}