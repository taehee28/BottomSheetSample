package com.example.bottomsheetsample.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomsheetsample.databinding.DialogFragmentBaseBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SampleBottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: DialogFragmentBaseBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = DialogFragmentBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}