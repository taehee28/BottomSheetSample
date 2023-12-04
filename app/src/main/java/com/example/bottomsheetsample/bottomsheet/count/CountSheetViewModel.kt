package com.example.bottomsheetsample.bottomsheet.count

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomsheetsample.bottomsheet.base.SheetScreenEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * [CountBottomSheet], [FirstCountSheetFragment], [SecondCountSheetFragment]가 공유하는 ViewModel
 */
class CountSheetViewModel : ViewModel() {

    init {
        Log.d("TAG", ">> ViewModel created")
    }

    // relay 값을 설정하면 처음 닫았을 때 방출된 CLOSE가
    // 바텀시트 다시 열면서 전달이 되어서
    // 바텀시트가 열리지 않고 다시 닫힘
    private val _screenFlow = MutableSharedFlow<SheetScreenEvent>()
    val screenFlow
        get() = _screenFlow.asSharedFlow()

    private val _count = MutableStateFlow(0)
    val count
        get() = _count.asStateFlow()

    fun increase() {
        _count.update { it + 1 }
    }

    fun decrease() {
        _count.update {
            if (it > 1) it - 1 else 0
        }
    }

    fun moveScreen(screen: SheetScreenEvent) = viewModelScope.launch {
        _screenFlow.emit(screen)
    }

    fun close() = viewModelScope.launch {
        _screenFlow.emit(SheetScreenEvent.CLOSE)
    }
}