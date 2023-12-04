package com.example.bottomsheetsample.bottomsheet.count

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.bottomsheetsample.bottomsheet.base.BaseSheetFragment
import com.example.bottomsheetsample.bottomsheet.base.SheetScreenEvent
import com.example.bottomsheetsample.bottomsheet.grandParentFragment
import com.example.bottomsheetsample.databinding.DialogFragmentCountFirstBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * [CountBottomSheet]에 처음으로 표시될 Fragment.
 */
class FirstCountSheetFragment : BaseSheetFragment<DialogFragmentCountFirstBinding>() {

    // 그냥 parentFragment를 얻으면 HostFragment가 나온다.
    // CountBottomSheet의 Fragment를 얻기 위해서 parent의 parent fragment를 요청.
    private val viewModel: CountSheetViewModel by viewModels({ grandParentFragment })
//    private val viewModel: CountSheetViewModel by activityViewModels()

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