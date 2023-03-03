 package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.pages

import android.os.Bundle
import android.view.View
import com.bgrebennikovv.github.inui.DefaultStateButton.ButtonStates
import com.bgrebennikovv.github.socialapp.databinding.FragmentSignupFullNamePageBaseBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.SignUpSharedFragment

class SignUpFullNamePageFragment : SignUpSharedFragment<FragmentSignupFullNamePageBaseBinding>(
    FragmentSignupFullNamePageBaseBinding::inflate
) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            nextBtn.setOnClickListener {
                authViewModel.firstName = firstName.text.toString()
                authViewModel.lastName = lastName.text.toString()

                nextBtn.setButtonState(ButtonStates.LOADING)
            }
        }

    }

}