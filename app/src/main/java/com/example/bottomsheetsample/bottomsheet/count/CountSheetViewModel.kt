package com.example.bottomsheetsample.bottomsheet.count

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountSheetViewModel : ViewModel() {

    init {
        Log.d("TAG", ">> ViewModel created")
    }

    private val _screenStep = MutableLiveData<CountSheetScreen>(CountSheetScreen.FIRST)
    val screenStep: LiveData<CountSheetScreen>
        get() = _screenStep

    private val _count = MutableLiveData<Int>(0)
    val count: LiveData<Int>
        get() = _count

    fun increase() {
        count.value?.also {
            _count.value = it + 1
        }
    }

    fun decrease() {
        count.value?.also {
            if (it > 1) {
                _count.value = it - 1
            }
        }
    }

    fun moveScreen(screen: CountSheetScreen) {
        _screenStep.value = screen
    }
}