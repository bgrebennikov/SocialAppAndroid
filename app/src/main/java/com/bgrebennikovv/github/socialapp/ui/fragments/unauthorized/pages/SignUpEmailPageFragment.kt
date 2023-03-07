package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.pages


import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.bgrebennikovv.github.inui.DefaultStateButton.ButtonStates
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.databinding.FragmentSignUpEmailPageBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.SignUpSharedFragment
import com.bgrebennikovv.github.socialapp.ui.viewModels.EmailCheckEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SignUpEmailPageFragment : SignUpSharedFragment<FragmentSignUpEmailPageBinding>(
    FragmentSignUpEmailPageBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.emailCheckFlow.onEach { event ->
            when(event){
                EmailCheckEvent.OnSuccess -> authViewModel.goNext()
                EmailCheckEvent.OnFailure -> binding.email.error = getString(R.string.email_already_exists)
            }
            binding.nextBtn.setButtonState(ButtonStates.DEFAULT)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.nextBtn.setOnClickListener {
            binding.nextBtn.setButtonState(ButtonStates.LOADING)
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
                binding.nextBtn.setButtonState(ButtonStates.DEFAULT)
            }
        }

    }

}