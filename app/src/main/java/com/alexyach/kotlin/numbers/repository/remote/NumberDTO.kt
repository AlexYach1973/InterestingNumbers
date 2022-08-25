package com.alexyach.kotlin.numbers.repository.remote


import com.google.gson.annotations.SerializedName

data class NumberDTO(
    @SerializedName("found")
    var found: Boolean,
    @SerializedName("number")
    var number: Int,
    @SerializedName("text")
    var text: String,
    @SerializedName("type")
    var type: String
)