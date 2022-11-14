package com.developerx.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.concurrent.Flow

@Dao
interface SubscriberDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: subscriber)

    @Update
    suspend fun updateSubscriber(subscriber: subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: subscriber)

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscriber() : LiveData<List<subscriber>>
}