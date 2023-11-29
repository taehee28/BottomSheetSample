package com.example.bottomsheetsample.bottomsheet.count

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navGraphViewModels
import com.example.bottomsheetsample.R
import com.example.bottomsheetsample.bottomsheet.BaseSheetFragment
import com.example.bottomsheetsample.bottomsheet.SheetScreenEvent
import com.example.bottomsheetsample.bottomsheet.grandParentFragment
import com.example.bottomsheetsample.databinding.DialogFragmentCountFirstBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FirstCountSheetFragment : BaseSheetFragment<DialogFragmentCountFirstBinding>() {

    // 그냥 parentFragment를 얻으면 HostFragment가 나온다.
    // CountBottomSheet의 Fragment를 얻기 위해서 parent의 parent fragment를 요청.
    private val viewModel: CountSheetViewModel by viewModels({ grandParentFragment })

    override fun getBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): DialogFragmentCountFirstBinding {
        return DialogFragmentCountFirstBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("TAG", ">> parent parent fragment = ${requireParentFragment().requireParentFragment()}")

        binding.apply {
            btnMinus.setOnClickListener {
                viewModel.decrease()
            }

            btnPlus.setOnClickListener {
                viewModel.increase()
            }

            btnNext.setOnClickListener {
                viewModel.moveScreen(screen = SheetScreenEvent.NEXT)
            }
        }

        observeCountValue()
    }

    private fun observeCountValue() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .count
                    .collectLatest {
                        binding.tvCount.text = it.toString()
                    }
            }
        }
    }
}