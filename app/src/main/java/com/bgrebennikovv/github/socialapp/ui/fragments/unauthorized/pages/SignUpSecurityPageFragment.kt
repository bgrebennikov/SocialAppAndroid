package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.pages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.databinding.FragmentSignUpSecurityPageBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.SignUpSharedFragment

class SignUpSecurityPageFragment : SignUpSharedFragment<FragmentSignUpSecurityPageBinding>(
    FragmentSignUpSecurityPageBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            Toast.makeText(context, authViewModel.email, Toast.LENGTH_SHORT).show()
            validator.validate {

                validatePassword(binding.password.text.toString()) {
                    onError {
                        binding.password.error = it
                    }
                    ifPasswordsNotIdentical(binding.password.text, binding.repeatPassword.text) {
                        binding.repeatPassword.error =
                            getString(R.string.repeat_password_error_message)
                    }
                    onSuccessCheck {
                        authViewModel.password = binding.password.text.toString()
                        authViewModel.goNext()
                    }
                }
            }
        }

    }


}