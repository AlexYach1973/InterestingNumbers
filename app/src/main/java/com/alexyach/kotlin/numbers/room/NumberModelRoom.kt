package com.alexyach.kotlin.numbers.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberModelRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val number: String,
    val fact: String
)
