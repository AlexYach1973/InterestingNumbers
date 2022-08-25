package com.alexyach.kotlin.numbers.repository

import com.alexyach.kotlin.numbers.model.NumberModel

interface ICallbackResponse {
    fun callbackSuccess(callbackNumber: NumberModel)
    fun callbackError(e: Exception)
}