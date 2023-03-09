package com.bgrebennikovv.github.socialapp.common.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bgrebennikovv.github.socialapp.R

fun Fragment.findRootNavController() : NavController{
    val navHost = requireActivity()
        .supportFragmentManager
        .findFragmentById(R.id.root_nav)
    as NavHostFragment?
    return navHost?.navController ?: findNavController()
}

fun Fragment.rootNavHost() : NavController{
    return requireActivity().findNavController(R.id.root_fragment_container)
}