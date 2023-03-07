package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SignUpPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle){

    private var pages = mutableListOf<Fragment>()

    fun addFragment(fragment: Fragment){
        pages.add(fragment)
    }

    override fun getItemCount() = pages.size

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }

}