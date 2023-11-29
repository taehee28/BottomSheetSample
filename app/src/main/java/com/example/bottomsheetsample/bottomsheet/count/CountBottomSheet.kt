package com.example.bottomsheetsample.bottomsheet.count

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.bottomsheetsample.R
import com.example.bottomsheetsample.bottomsheet.BaseBottomSheetDialog
import com.example.bottomsheetsample.bottomsheet.SheetScreenEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CountBottomSheet : BaseBottomSheetDialog() {

    private val viewModel: CountSheetViewModel by viewModels()

    // TODO: Base로 빼기?
    private lateinit var navController: NavController

    override fun initView() {
        initNavController()
        observeScreenFlow()
    }

    /**
     * navController 초기화.
     * (그냥 findNavController 하면 못찾음)
     */
    private fun initNavController() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun observeScreenFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .screenFlow
                    .distinctUntilChanged()
                    .collectLatest {
                        Log.d("CountBottomSheet", ">> screen : ${it.name}")

                        if (it == SheetScreenEvent.CLOSE) {
                            this@CountBottomSheet.dismiss()
                        } else {
                            changeScreen(screen = it)
                        }
                    }
            }
        }
    }

    private fun changeScreen(screen: SheetScreenEvent) {
        when (screen) {
            SheetScreenEvent.NEXT -> {
                // 스텝이 여러개인 경우 화면마다 다음으로 넘어가는 action의 아이디를 통일시켜서
                // 어떤 화면이라도 같은 action 아이디를 사용하도록 하기
                navController.navigate(R.id.action_firstCountSheetFragment_to_secondCountSheetFragment)
            }
            SheetScreenEvent.PREV -> {
                navController.popBackStack()
            }
            else -> throw IllegalArgumentException("없는 화면")
        }
    }
}