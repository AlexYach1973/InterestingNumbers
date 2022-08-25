package com.alexyach.kotlin.numbers.repository

interface IGetDetailsByNumber {
    fun getDetailsByNumber(number: String, callbackResponse: ICallbackResponse)
}