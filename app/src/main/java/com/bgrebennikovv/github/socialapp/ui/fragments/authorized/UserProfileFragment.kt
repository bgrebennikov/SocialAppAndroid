package com.bgrebennikovv.github.socialapp.ui.fragments.authorized

import android.os.Bundle
import android.view.View
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.common.extensions.setRootGraph
import com.bgrebennikovv.github.socialapp.databinding.FragmentUserProfileBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.BaseFragment


class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>(
    FragmentUserProfileBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutBtn.setOnClickListener {
            settingsViewModel.logoutUser{
                setRootGraph(R.navigation.unauthorized_nav)
            }
        }
    }

}