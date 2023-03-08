package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.pages


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bgrebennikovv.github.inui.DefaultStateButton.ButtonStates
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.databinding.FragmentSignUpEmailPageBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.SignUpSharedFragment
import com.bgrebennikovv.github.socialapp.ui.viewModels.EmailCheckEvent
import kotlinx.coroutines.launch

class SignUpEmailPageFragment : SignUpSharedFragment<FragmentSignUpEmailPageBinding>(
    FragmentSignUpEmailPageBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch{
            authViewModel.emailCheckFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect{ event ->
                    when (event) {
                        EmailCheckEvent.OnStart -> binding.nextBtn.setButtonState(ButtonStates.LOADING)
                        EmailCheckEvent.OnSuccess -> authViewModel.goNext()
                        EmailCheckEvent.OnFailure -> binding.email.error =
                            getString(R.string.email_already_exists)
                        EmailCheckEvent.OnDone -> binding.nextBtn.setButtonState(ButtonStates.DEFAULT)
                    }

                }
        }

        binding.nextBtn.setOnClickListener {

            validator.validate {
                validateEmail(binding.email.text.toString()) {
                    onError {
                        binding.email.error = it
                        binding.nextBtn.setButtonState(ButtonStates.DEFAULT)
                    }
                    onSuccessCheck {
                        authViewModel.email = binding.email.text.toString()
                        authViewModel.checkEmailExists(binding.email.text.toString())
                    }
                }
            }
        }

    }

}