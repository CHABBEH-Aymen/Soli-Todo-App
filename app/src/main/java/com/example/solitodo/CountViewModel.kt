package com.example.solitodo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CountViewModel(): ViewModel() {
    private var _count = mutableIntStateOf(0)
    val count: State<Int> get() = _count

    fun increment() {
        _count.intValue.inc()
    }

    fun decrement() {
        _count.intValue.dec()
    }
}
