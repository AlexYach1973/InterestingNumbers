package com.alexyach.kotlin.numbers.repository

import com.alexyach.kotlin.numbers.model.NumberModel

fun interface IGetNumbersListFromRoom {
    fun getNumbersAll(callbackResponse: (List<NumberModel>) -> Unit )
}