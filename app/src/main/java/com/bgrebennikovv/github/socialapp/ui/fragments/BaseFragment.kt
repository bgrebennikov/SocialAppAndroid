package com.bgrebennikovv.github.socialapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.ui.viewModels.AppSettingsViewModel
import org.koin.android.ext.android.inject

open class BaseFragment<T : ViewBinding>(
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    val settingsViewModel : AppSettingsViewModel by inject()

    private val navHostRoot by lazy {
        requireActivity().supportFragmentManager.findFragmentById(R.id.root_fragment_container) as NavHostFragment
    }

    private var _binding : T? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateMethod.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setRootGraph(graphId : Int){
        with(navHostRoot.navController){
            graph = navInflater.inflate(graphId)
        }
    }

}