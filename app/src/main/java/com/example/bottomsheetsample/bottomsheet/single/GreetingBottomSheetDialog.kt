package com.example.bottomsheetsample.bottomsheet.single

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bottomsheetsample.bottomsheet.base.BaseSingleBottomSheetDialog
import com.example.bottomsheetsample.databinding.DialogBottomSheetGreetingBinding

class GreetingBottomSheetDialog : BaseSingleBottomSheetDialog<DialogBottomSheetGreetingBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogBottomSheetGreetingBinding {
        return DialogBottomSheetGreetingBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}