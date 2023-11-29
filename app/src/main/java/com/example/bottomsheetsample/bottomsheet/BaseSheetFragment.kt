package com.example.bottomsheetsample.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * 바텀 시트에 표시할 내용이 되는 Fragment들의 Base 클래스.
 */
abstract class BaseSheetFragment<VB: ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val binding
        get() = _binding!!

    abstract fun getBinding(layoutInflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = getBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}