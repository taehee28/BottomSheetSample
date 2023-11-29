package com.example.bottomsheetsample.bottomsheet

import androidx.fragment.app.Fragment

val Fragment.grandParentFragment
    get() = requireParentFragment().requireParentFragment()