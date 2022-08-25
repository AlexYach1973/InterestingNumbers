package com.alexyach.kotlin.numbers.utils

import com.alexyach.kotlin.numbers.model.NumberModel
import com.alexyach.kotlin.numbers.repository.remote.NumberDTO
import com.alexyach.kotlin.numbers.room.NumberModelRoom

fun converterNumberModelRoomToNumberModel(modelRoom: NumberModelRoom): NumberModel {
    return NumberModel(
        number = modelRoom.number,
        fact = modelRoom.fact
    )
}

fun converterNumberDTOToNumberModel(modelDTO: NumberDTO) : NumberModel {
    return NumberModel(
        number = modelDTO.number.toString(),
        fact = modelDTO.text
    )
}

fun converterNumberModelToModelRoom(numberModel: NumberModel) : NumberModelRoom {
    return NumberModelRoom(
        0,
        numberModel.number,
        numberModel.fact
    )
}