package com.bgrebennikovv.github.socialapp.ui.fragments.authorized

import android.os.Bundle
import android.view.View
import androidx.navigation.navOptions
import androidx.navigation.ui.NavigationUI
import com.bgrebennikovv.github.socialapp.databinding.FragmentUserHomeContainerBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.BaseFragment

class UserHomeContainerFragment : BaseFragment<FragmentUserHomeContainerBinding>(
    FragmentUserHomeContainerBinding::inflate
) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.bottomNavigationView) {
            setOnItemReselectedListener {
                bottomNavHost?.navigate(it.itemId, null, navOptions {
                    popUpTo(it.itemId) {
                        inclusive = true
                    }
                })
            }

            bottomNavHost?.let { NavigationUI.setupWithNavController(this, it) }
        }

    }

}