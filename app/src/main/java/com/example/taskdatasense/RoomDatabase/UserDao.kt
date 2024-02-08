package com.example.taskdatasense.RoomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM testuserdatatable")
    fun getAllData(): List<UserDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun getInsertData(vararg userDetails: UserDetails)
}
