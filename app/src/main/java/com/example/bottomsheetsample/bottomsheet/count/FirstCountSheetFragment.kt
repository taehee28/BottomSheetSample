package com.example.bottomsheetsample.bottomsheet.count

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bottomsheetsample.bottomsheet.BaseSheetFragment
import com.example.bottomsheetsample.databinding.DialogFragmentCountFirstBinding

class FirstCountSheetFragment : BaseSheetFragment<DialogFragmentCountFirstBinding>() {
    override fun getBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): DialogFragmentCountFirstBinding {
        return DialogFragmentCountFirstBinding.inflate(layoutInflater, container, false)
    }


}