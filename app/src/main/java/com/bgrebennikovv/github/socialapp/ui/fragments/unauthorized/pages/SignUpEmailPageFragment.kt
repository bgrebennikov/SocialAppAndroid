package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.pages


import android.os.Bundle
import android.view.View
import com.bgrebennikovv.github.socialapp.databinding.FragmentSignUpEmailPageBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.SignUpSharedFragment

class SignUpEmailPageFragment : SignUpSharedFragment<FragmentSignUpEmailPageBinding>(
    FragmentSignUpEmailPageBinding::inflate
) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            validator.validate {
                validateEmail(binding.email.text.toString()){
                    onError {
                        binding.email.error = it
                    }
                    onSuccessCheck {
                        authViewModel.email = binding.email.text.toString()
                        authViewModel.goNext()
                    }
                }
            }
        }

    }

}