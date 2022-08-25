package com.alexyach.kotlin.numbers.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface INumberDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNumber(numberModelRoom: NumberModelRoom)

    @Query("SELECT * FROM NumberModelRoom")
    fun getNumbersAll() : List<NumberModelRoom>

    @Query("SELECT * FROM NumberModelRoom WHERE number=:number")
    fun getNumberById(number: String) : NumberModelRoom

    @Query("DELETE FROM NumberModelRoom")
    fun deleteAll()

}