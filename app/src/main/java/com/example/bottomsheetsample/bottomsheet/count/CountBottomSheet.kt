package com.example.bottomsheetsample.bottomsheet.count

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.bottomsheetsample.bottomsheet.BaseBottomSheetDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CountBottomSheet : BaseBottomSheetDialog() {

    private val viewModel: CountSheetViewModel by viewModels()

    override fun initView() {
        childFragmentManager
            .beginTransaction()
            .add(binding.root.id, SecondCountSheetFragment(), CountSheetScreen.SECOND.name)
            .add(binding.root.id, FirstCountSheetFragment(), CountSheetScreen.FIRST.name)
            .commit()

        viewModel.moveScreen(CountSheetScreen.FIRST)

        observeScreenFlow()
    }

    private fun observeScreenFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .screenFlow
                    .distinctUntilChanged()
                    .collectLatest {
                        Log.d("CountBottomSheet", ">> screen : ${it.name}")

                        if (it == CountSheetScreen.CLOSE) {
                            this@CountBottomSheet.dismiss()
                        } else {
                            changeScreen(screen = it)
                        }
                    }
            }
        }
    }

    private fun changeScreen(screen: CountSheetScreen) {
        val (nextFrag, currentFrag) = when (screen) {
            CountSheetScreen.FIRST -> {
                childFragmentManager.findFragmentByTag(CountSheetScreen.FIRST.name) to
                        childFragmentManager.findFragmentByTag(CountSheetScreen.SECOND.name)
            }
            CountSheetScreen.SECOND -> {
                childFragmentManager.findFragmentByTag(CountSheetScreen.SECOND.name) to
                        childFragmentManager.findFragmentByTag(CountSheetScreen.FIRST.name)
            }
            else -> throw IllegalArgumentException("없는 화면")
        }

        childFragmentManager
            .beginTransaction()
            .hide(currentFrag!!)
            .show(nextFrag!!)
            .commit()
    }
}