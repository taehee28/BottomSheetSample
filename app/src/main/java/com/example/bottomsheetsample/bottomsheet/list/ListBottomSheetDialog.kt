package com.example.bottomsheetsample.bottomsheet.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bottomsheetsample.bottomsheet.base.BaseSingleBottomSheetDialog
import com.example.bottomsheetsample.databinding.DialogBottomSheetListBinding

class ListBottomSheetDialog : BaseSingleBottomSheetDialog<DialogBottomSheetListBinding>() {

    private val adapter: ColorListAdapter by lazy { ColorListAdapter() }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogBottomSheetListBinding {
        return DialogBottomSheetListBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }

        binding.rvList.adapter = adapter
        adapter.submitList(pinkList)
    }
}

