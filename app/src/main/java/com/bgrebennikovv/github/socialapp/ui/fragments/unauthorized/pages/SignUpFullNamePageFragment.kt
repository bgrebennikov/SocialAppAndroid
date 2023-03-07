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

                validator.validate {

                    validateText(binding.firstName.text.toString()){
                        onError {
                            binding.firstName.error = it
                        }
                    }

                    validateText(binding.lastName.text.toString()){
                        onError {
                            binding.lastName.error = it
                        }
                    }

                    onSuccessCheck {
                        authViewModel.firstName = firstName.text.toString()
                        authViewModel.lastName = lastName.text.toString()

                        nextBtn.setButtonState(ButtonStates.LOADING)
                        authViewModel.signUp()
                    }

                }
            }
        }

    }

    override fun onPause() {
        super.onPause()
        binding.nextBtn.setButtonState(ButtonStates.DEFAULT)
    }

}