package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.common.extensions.findRootNavController
import com.bgrebennikovv.github.socialapp.data.models.login.StatusResponse
import com.bgrebennikovv.github.socialapp.databinding.FragmentLoginBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.BaseFragment
import com.bgrebennikovv.github.socialapp.ui.viewModels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent


class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
), KoinComponent {

    private val authViewModel: AuthViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        authViewModel.getLoginResult().observe(viewLifecycleOwner) {
            when (it.status) {
                StatusResponse.LOADING -> Toast.makeText(
                    context,
                    "Loading...",
                    Toast.LENGTH_SHORT
                ).show()

                StatusResponse.API_ERROR -> {
                    Toast.makeText(context, it.errors[0].message, Toast.LENGTH_SHORT).show()
                }

                StatusResponse.SUCCESS -> {
                    setRootGraph(R.navigation.authorized_nav)
                }
                else -> {

                }
            }
        }

        binding.signUp.setOnClickListener {
            findRootNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            )
        }


        binding.loginBtn.setOnClickListener {
            authViewModel.login(
                binding.email.text.toString(),
                binding.password.text.toString()
            )
        }

    }
}