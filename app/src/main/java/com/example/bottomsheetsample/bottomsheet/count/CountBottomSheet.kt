package com.example.bottomsheetsample.bottomsheet.count

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.bottomsheetsample.bottomsheet.BaseBottomSheetDialog

class CountBottomSheet : BaseBottomSheetDialog() {

    private val viewModel: CountSheetViewModel by viewModels()

    override fun initView() {
        childFragmentManager
            .beginTransaction()
            .add(binding.root.id, SecondCountSheetFragment(), CountSheetScreen.SECOND.name)
            .add(binding.root.id, FirstCountSheetFragment(), CountSheetScreen.FIRST.name)
            .commit()

        observeScreenStep()
    }

    private fun observeScreenStep() {
        viewModel.screenStep.observe(viewLifecycleOwner) {
            Log.d("CountBottomSheet", ">> screen : ${it.name}")

            it ?: return@observe

            val (nextFrag, currentFrag) = when (it) {
                CountSheetScreen.FIRST -> {
                    childFragmentManager.findFragmentByTag(CountSheetScreen.FIRST.name) to
                            childFragmentManager.findFragmentByTag(CountSheetScreen.SECOND.name)
                }
                CountSheetScreen.SECOND -> {
                    childFragmentManager.findFragmentByTag(CountSheetScreen.SECOND.name) to
                            childFragmentManager.findFragmentByTag(CountSheetScreen.FIRST.name)
                }
            }

            childFragmentManager
                .beginTransaction()
                .hide(currentFrag!!)
                .show(nextFrag!!)
                .commit()
        }
    }
}