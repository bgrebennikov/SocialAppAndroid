package com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import com.bgrebennikovv.github.socialapp.common.extensions.findRootNavController
import com.bgrebennikovv.github.socialapp.databinding.FragmentSignUpBinding
import com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.adapters.SignUpPagerAdapter
import com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.pages.SignUpEmailPageFragment
import com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.pages.SignUpFullNamePageFragment
import com.bgrebennikovv.github.socialapp.ui.fragments.unauthorized.pages.SignUpSecurityPageFragment
import com.bgrebennikovv.github.socialapp.ui.viewModels.Event
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent


class SignUpFragment : SignUpSharedFragment<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
), KoinComponent {

    private val pagerAdapter by lazy {
        SignUpPagerAdapter(
            childFragmentManager,
            lifecycle
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pagerAdapter.apply {
            addFragment(SignUpEmailPageFragment())
            addFragment(SignUpSecurityPageFragment())
            addFragment(SignUpFullNamePageFragment())
        }

        handleOnBackPress()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViewPager()

        authViewModel.eventsFlow
            .onEach { event ->
                when(event){
                    is Event.GoNext -> {
                        this.goNext()
                    }

                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun initToolbar() {
        binding.toolbar.apply {
            setOnBackPressAction {
                findRootNavController().popBackStack()
            }
        }
    }

    private fun initViewPager() {
        binding.signupViewPager.apply {
            adapter = pagerAdapter
            isUserInputEnabled = false
            offscreenPageLimit = 2
        }
    }

    private fun goNext() {
        val nextPos = if ((binding.signupViewPager.currentItem + 1) < pagerAdapter.itemCount) {
            binding.signupViewPager.currentItem + 1
        } else return

        binding.signupViewPager.currentItem = nextPos
    }

    private fun handleOnBackPress() {
        activity?.onBackPressedDispatcher?.addCallback(
            this@SignUpFragment,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.signupViewPager.currentItem > 0) {
                        binding.signupViewPager.currentItem =
                            binding.signupViewPager.currentItem - 1
                    } else findRootNavController().popBackStack()
                }
            })
    }

}