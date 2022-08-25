package com.alexyach.kotlin.numbers.repository.local

import com.alexyach.kotlin.numbers.NumbersApp.Companion.getNumbersDataBase
import com.alexyach.kotlin.numbers.model.NumberModel
import com.alexyach.kotlin.numbers.repository.ICallbackResponse
import com.alexyach.kotlin.numbers.repository.IGetDetailsByNumber
import com.alexyach.kotlin.numbers.repository.IGetNumbersListFromRoom
import com.alexyach.kotlin.numbers.room.NumberModelRoom
import com.alexyach.kotlin.numbers.utils.converterNumberModelRoomToNumberModel
import com.alexyach.kotlin.numbers.utils.converterNumberModelToModelRoom
import java.io.IOException

class RepositoryLocalImpl : IGetDetailsByNumber, IGetNumbersListFromRoom {

    override fun getNumbersAll(callbackResponse: (List<NumberModel>) -> Unit) {

        val numbersList: List<NumberModelRoom> =
            getNumbersDataBase().numberDao().getNumbersAll()

        if (numbersList.isNotEmpty()) {
            callbackResponse(numbersList.map {
                converterNumberModelRoomToNumberModel(it)
            })
        } else {
            emptyList<NumberModel>()
        }
    }

    override fun getDetailsByNumber(number: String, callbackResponse: ICallbackResponse) {

        val numberInfo: NumberModelRoom =
            getNumbersDataBase().numberDao().getNumberById(number)

        if (numberInfo != null) {
            callbackResponse.callbackSuccess(
                converterNumberModelRoomToNumberModel(numberInfo)
            )
        } else {
            callbackResponse.callbackError(IOException("Немає такого числа в локальній базі даних"))
        }
    }

    fun deleteAllFromLocal() {
        getNumbersDataBase().numberDao().deleteAll()
    }

    fun saveNumberToRoom(numberModel: NumberModel) {
        getNumbersDataBase().numberDao().insertNumber(
            converterNumberModelToModelRoom(numberModel)
        )
    }
}