package com.example.bottomsheetsample.bottomsheet.count

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomsheetsample.bottomsheet.SheetScreenEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountSheetViewModel : ViewModel() {

    init {
        Log.d("TAG", ">> ViewModel created")
    }

    private val _screenFlow = MutableSharedFlow<SheetScreenEvent>(replay = 1)
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