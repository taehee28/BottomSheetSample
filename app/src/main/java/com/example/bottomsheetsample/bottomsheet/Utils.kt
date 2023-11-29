package com.example.bottomsheetsample.bottomsheet

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

val Fragment.grandParentFragment
    get() = requireParentFragment().requireParentFragment()

fun Fragment.findNavHostFragment(@IdRes id: Int) =
    childFragmentManager.findFragmentById(id) as NavHostFragment