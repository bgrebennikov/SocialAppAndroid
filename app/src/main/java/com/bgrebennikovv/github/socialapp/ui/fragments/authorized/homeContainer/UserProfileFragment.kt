package com.bgrebennikovv.github.socialapp.ui.fragments.authorized.homeContainer

import android.os.Bundle
import android.view.View
import androidx.navigation.navOptions
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.databinding.FragmentUserProfileBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.BaseFragment


class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>(
    FragmentUserProfileBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutBtn.setOnClickListener {
            settingsViewModel.logoutUser{
                rootNavHost.navigate(
                    R.id.unauthorized_nav,
                    null,
                    navOptions {
                        popUpTo(R.id.unauthorized_nav){
                            inclusive = false
                        }
                    }
                )
            }
        }
    }

}