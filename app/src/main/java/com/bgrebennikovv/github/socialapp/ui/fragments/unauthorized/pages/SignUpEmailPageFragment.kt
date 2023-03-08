package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.pages


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bgrebennikovv.github.inui.DefaultStateButton.ButtonStates
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.data.models.login.StatusResponse
import com.bgrebennikovv.github.socialapp.databinding.FragmentSignUpEmailPageBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.SignUpSharedFragment
import com.bgrebennikovv.github.socialapp.ui.viewModels.EmailCheckEvent
import kotlinx.coroutines.launch

class SignUpEmailPageFragment : SignUpSharedFragment<FragmentSignUpEmailPageBinding>(
    FragmentSignUpEmailPageBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            authViewModel.emailCheckFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { event ->
                    when (event) {
                        is EmailCheckEvent.OnLoading -> btnSetLoading()
                        is EmailCheckEvent.OnDone -> {
                            when (event.response.status) {
                                StatusResponse.SUCCESS -> {
                                    if (event.response.response?.success == true) authViewModel.goNext()
                                    else binding.email.error =
                                        getString(R.string.email_already_exists)
                                }
                                StatusResponse.API_ERROR -> binding.email.error =
                                    event.response.errors.first().message
                                StatusResponse.EXCEPTION -> {
                                    event.response.errors.firstOrNull()?.message?.let {
                                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                                    }
                                }
                                else -> {}
                            }
                            btnSetDefault()
                        }
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

    private fun btnSetLoading() {
        binding.nextBtn.setButtonState(ButtonStates.LOADING)
    }

    private fun btnSetDefault() {
        binding.nextBtn.setButtonState(ButtonStates.DEFAULT)
    }

}