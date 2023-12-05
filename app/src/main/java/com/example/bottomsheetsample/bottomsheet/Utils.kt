package com.example.bottomsheetsample.bottomsheet

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

val Fragment.grandParentFragment
    get() = requireParentFragment().requireParentFragment()

fun Fragment.findNavHostFragment(@IdRes id: Int) =
    childFragmentManager.findFragmentById(id) as NavHostFragment

fun BottomSheetDialogFragment.show(manager: FragmentManager) {
    this.show(manager, "")
}