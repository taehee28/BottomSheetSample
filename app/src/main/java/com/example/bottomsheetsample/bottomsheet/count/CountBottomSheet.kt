package com.example.bottomsheetsample.bottomsheet.count

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.bottomsheetsample.R
import com.example.bottomsheetsample.bottomsheet.base.BaseBottomSheetDialog
import com.example.bottomsheetsample.bottomsheet.base.SheetScreenEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/**
 * 예제로 만든 바텀 시트 다이얼로그.
 * 바텀 시트 위에 얹어지는 Fragment들과 ViewModel을 통해서 데이터를 주고받음. 
 */
class CountBottomSheet : BaseBottomSheetDialog() {

    private val viewModel: CountSheetViewModel by viewModels()
//    private val viewModel: CountSheetViewModel by activityViewModels()

    override val navGraphId: Int
        get() = R.navigation.count_bottom_sheet_nav_graph

    override fun initView() {
        observeScreenFlow()
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