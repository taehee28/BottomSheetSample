package com.example.bottomsheetsample.bottomsheet.count

import com.example.bottomsheetsample.bottomsheet.BaseBottomSheetDialog

class CountBottomSheet : BaseBottomSheetDialog() {

    override fun initView() {
        childFragmentManager.beginTransaction().add(binding.root.id, FirstCountSheetFragment()).commit()
    }
}