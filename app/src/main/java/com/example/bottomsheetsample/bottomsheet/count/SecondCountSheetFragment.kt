package com.example.bottomsheetsample.bottomsheet.count

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bottomsheetsample.bottomsheet.BaseSheetFragment
import com.example.bottomsheetsample.databinding.DialogFragmentCountSecondBinding

class SecondCountSheetFragment : BaseSheetFragment<DialogFragmentCountSecondBinding>() {
    override fun getBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): DialogFragmentCountSecondBinding {
        return DialogFragmentCountSecondBinding.inflate(layoutInflater, container, false)
    }
}