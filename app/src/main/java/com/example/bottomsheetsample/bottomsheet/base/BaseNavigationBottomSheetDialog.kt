package com.example.bottomsheetsample.bottomsheet.base

import android.os.Bundle
import android.view.View
import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.bottomsheetsample.R
import com.example.bottomsheetsample.bottomsheet.findNavHostFragment

abstract class BaseNavigationBottomSheetDialog : BaseBottomSheetDialog() {
    @get:NavigationRes
    protected abstract val navGraphId: Int

    protected val navController: NavController by lazy {
        findNavHostFragment(R.id.fragment_container).findNavController()
    }

    protected abstract fun initialize()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainer.id, NavHostFragment())
            .commitNow()

        navController.setGraph(navGraphId)

        initialize()
    }
}