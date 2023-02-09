package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.data.models.login.StatusResponse
import com.bgrebennikovv.github.socialapp.databinding.FragmentLoginBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.BaseFragment
import com.bgrebennikovv.github.socialapp.ui.viewModels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent


class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
), KoinComponent {

    private val loginViewModel: LoginViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loginViewModel.getLoginResult().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                StatusResponse.LOADING -> Toast.makeText(
                    context,
                    "Loading...",
                    Toast.LENGTH_SHORT
                ).show()

                StatusResponse.API_ERROR -> {
                    binding.emailInput.error = it.errors[0].message
                }

                StatusResponse.SUCCESS -> {
                    setRootGraph(R.navigation.authorized_nav)
                }
                else -> {

                }
            }
        })


        binding.loginBtn.setOnClickListener {
            loginViewModel.login(
                binding.email.text.toString(),
                binding.password.text.toString()
            )
        }

    }
}