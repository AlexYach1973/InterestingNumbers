package com.alexyach.kotlin.numbers

import android.app.Application
import androidx.room.Room
import com.alexyach.kotlin.numbers.room.NumbersDataBase

class NumbersApp : Application() {
    override fun onCreate() {
        super.onCreate()
        numbersApp = this
    }

    companion object {
        // Context
        private var numbersApp: NumbersApp? = null
        fun getNumbersApp() = numbersApp!!

        // DB
        private var numbersDataBae: NumbersDataBase? = null
        fun getNumbersDataBase(): NumbersDataBase {

            if (numbersDataBae == null) {
                numbersDataBae = Room.databaseBuilder(
                    getNumbersApp(),
                    NumbersDataBase::class.java,
                    "NumbersDataBase"
                ).build()
            }

            return numbersDataBae!!
        }
    }
}