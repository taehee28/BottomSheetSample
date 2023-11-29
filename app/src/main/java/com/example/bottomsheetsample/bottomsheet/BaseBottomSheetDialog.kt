package com.example.bottomsheetsample.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.bottomsheetsample.R
import com.example.bottomsheetsample.databinding.DialogBottomSheetBaseBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * 커스텀 바텀 시트의 틀이 되는 Base 클래스.
 *
 */
abstract class BaseBottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: DialogBottomSheetBaseBinding? = null
    protected val binding
        get() = _binding!!

    protected val navController: NavController by lazy {
        findNavHostFragment(R.id.fragment_container).findNavController()
    }

    @get:NavigationRes
    protected abstract val navGraphId: Int

    abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = DialogBottomSheetBaseBinding.inflate(inflater, container, false)

        setNavGraph()

        return binding.root
    }

    private fun setNavGraph() {
        navController.setGraph(navGraphId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 바텀시트 내용이 커도 한번에 펼쳐지도록, 중간에 걸리지 않도록 설정
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnShowListener { dialogInterface ->
            val _dialog = dialogInterface as BottomSheetDialog

            _dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)?.also {
                BottomSheetBehavior.from(it).apply {
                    state = BottomSheetBehavior.STATE_EXPANDED
                    skipCollapsed = true
                }

            }
        }

        return dialog
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}