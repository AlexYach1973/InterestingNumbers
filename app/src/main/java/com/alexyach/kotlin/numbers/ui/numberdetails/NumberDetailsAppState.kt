package com.alexyach.kotlin.numbers.ui.numberdetails

import com.alexyach.kotlin.numbers.model.NumberModel

sealed class NumberDetailsAppState {
    data class SuccessGetDetails(val numberInfo: NumberModel): NumberDetailsAppState()
    data class ErrorGetDetails(val errorString: String): NumberDetailsAppState()

    object Loading: NumberDetailsAppState()
}
