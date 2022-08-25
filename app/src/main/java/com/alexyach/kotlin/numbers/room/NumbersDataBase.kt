package com.alexyach.kotlin.numbers.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberModelRoom::class], version = 1)
abstract class NumbersDataBase : RoomDatabase() {
    abstract fun numberDao() : INumberDAO
}