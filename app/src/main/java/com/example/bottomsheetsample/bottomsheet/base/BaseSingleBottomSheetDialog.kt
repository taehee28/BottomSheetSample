package com.example.bottomsheetsample.bottomsheet.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * 화면 이동을 하지 않는 바텀시트의 Base 클래스.
 * 바텀시트 UI 표시에 Fragment를 사용하지 않고 바텀시트에서 직접 표시해야함.
 */
abstract class BaseSingleBottomSheetDialog<VB : ViewBinding> : BaseBottomSheetDialog() {

    /*
    Fragment를 사용하게 되면 바텀시트 종류 하나 추가할때마다
    BottomSheetDialog, SheetFragment, ViewModel의 서브클래스들을 만들어줘야함.
     */

    private var _binding: VB? = null
    protected val binding
        get() = _binding!!

    protected abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    protected abstract fun initialize()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        _binding = getBinding(inflater, container)
        baseBinding.apply {
            fragmentContainer.visibility = View.GONE
            layoutFrame.addView(binding.root)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}