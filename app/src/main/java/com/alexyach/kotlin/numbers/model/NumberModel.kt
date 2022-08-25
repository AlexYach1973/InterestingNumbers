package com.alexyach.kotlin.numbers.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NumberModel(
    val number: String,
    val fact: String
): Parcelable
