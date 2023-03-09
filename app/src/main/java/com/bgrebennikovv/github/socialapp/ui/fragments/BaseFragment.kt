package com.bgrebennikovv.github.socialapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.ui.viewModels.AppSettingsViewModel
import com.bgrebennikovv.github.socialapp.ui.viewModels.AuthViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

open class BaseFragment<T : ViewBinding>(
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    val rootNavHost by lazy {
        requireActivity().findNavController(R.id.root_fragment_container)
    }

    val bottomNavHost : NavController? by lazy {
        childFragmentManager.findFragmentById(R.id.user_home_container)?.findNavController()
    }

    val settingsViewModel : AppSettingsViewModel by inject()
    val authViewModel : AuthViewModel by viewModel()

    private var _binding : T? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateMethod.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}