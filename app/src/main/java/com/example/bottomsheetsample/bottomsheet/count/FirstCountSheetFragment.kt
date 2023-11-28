package com.example.bottomsheetsample.bottomsheet.count

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.bottomsheetsample.bottomsheet.BaseSheetFragment
import com.example.bottomsheetsample.databinding.DialogFragmentCountFirstBinding

class FirstCountSheetFragment : BaseSheetFragment<DialogFragmentCountFirstBinding>() {

    private val viewModel: CountSheetViewModel by viewModels({ requireParentFragment() })

    override fun getBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): DialogFragmentCountFirstBinding {
        return DialogFragmentCountFirstBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnMinus.setOnClickListener {
                viewModel.decrease()
            }

            btnPlus.setOnClickListener {
                viewModel.increase()
            }

            btnNext.setOnClickListener {
                viewModel.moveScreen(screen = CountSheetScreen.SECOND)
            }
        }

        observeCountValue()
    }

    private fun observeCountValue() {
        viewModel.count.observe(viewLifecycleOwner) {
            it ?: return@observe

            binding.tvCount.text = it.toString()
        }
    }
}