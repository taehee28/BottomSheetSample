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
import com.example.bottomsheetsample.databinding.DialogFragmentCountSecondBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * [CountBottomSheet]에 두번째로 표시될 Fragment.
 */
class SecondCountSheetFragment : BaseSheetFragment<DialogFragmentCountSecondBinding>() {

    private val viewModel: CountSheetViewModel by viewModels({ grandParentFragment })
//    private val viewModel: CountSheetViewModel by activityViewModels()

    override fun getBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): DialogFragmentCountSecondBinding {
        return DialogFragmentCountSecondBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnPrev.setOnClickListener {
                viewModel.moveScreen(screen = SheetScreenEvent.PREV)
            }

            btnClose.setOnClickListener {
                viewModel.close()
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