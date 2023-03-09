package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized

import android.os.Bundle
import android.view.View
import com.bgrebennikovv.github.inputvalidator.InputValidator
import com.bgrebennikovv.github.inui.DefaultStateButton.ButtonStates
import com.bgrebennikovv.github.inui.alerts.InAlert
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.common.extensions.findRootNavController
import com.bgrebennikovv.github.socialapp.data.models.login.StatusResponse
import com.bgrebennikovv.github.socialapp.databinding.FragmentLoginBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.BaseFragment
import org.koin.core.component.KoinComponent


class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
), KoinComponent {

    private val inAlert by lazy {
        InAlert(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.getAuthResult().observe(viewLifecycleOwner) { response ->
            response?.let {
                when (it.status) {
                    StatusResponse.LOADING -> {
                        binding.loginBtn.setButtonState(ButtonStates.LOADING)
                    }

                    StatusResponse.API_ERROR -> {
                        binding.loginBtn.setButtonState(ButtonStates.DEFAULT)

                        inAlert{
                            title = context?.getString(R.string.login_fail_message_title)
                            textColor = requireContext().getColor(R.color.textColor)
                            backgroundColor = requireContext().getColor(R.color.bgColor)
                            body = it.errors.first().message

                            buttonText = requireContext().getString(R.string.login_close_alert_btn_text)
                            onConfirm = {
                                binding.password.text?.clear()
                                dismiss()
                            }

                        }.show()

                    }

                    StatusResponse.SUCCESS -> {
                        rootNavHost.navigate(
                            LoginFragmentDirections.xml()
                        )
                    }
                    else -> {

                    }
                }
            }
        }

        binding.signUp.setOnClickListener {
            findRootNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            )
        }

        with(binding) {
            loginBtn.setOnClickListener {
                InputValidator.validate {
                    validatePassword(binding.password.text.toString()) {
                        onError {
                            password.error = it
                        }
                    }
                    validateEmail(binding.email.text.toString()) {
                        params {
                            allowedDomains = listOf(
                                "gmail.com"
                            )
                        }
                        onError {
                            email.error = it
                        }
                    }
                    onSuccessCheck {
                        authViewModel.login(
                            email.text.toString(),
                            password.text.toString()
                        )
                    }
                }

            }

        }


    }
}