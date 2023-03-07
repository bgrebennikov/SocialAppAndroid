package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bgrebennikovv.github.inputvalidator.InputValidator
import com.bgrebennikovv.github.socialapp.ui.viewModels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.core.component.KoinComponent

open class SignUpSharedFragment<T : ViewBinding>(
    private val inflateMethod : (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment(), KoinComponent {

    val authViewModel : AuthViewModel by activityViewModel()

    val validator = InputValidator

    private var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateMethod.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.viewModelStore?.clear()
    }

}