package com.example.planyourlife.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.planyourlife.data.entity.TimeLineTask

@Dao
interface TimeLineTaskDAO
{
    @Insert
    suspend fun insertTask(task: TimeLineTask)

    @Query("SELECT * FROM TimeLineTask")
    suspend fun uploadAllTimeLimeTasks(): List<TimeLineTask>

    @Query("SELECT * FROM TimeLineTask WHERE Day = :date")
    suspend fun uploadTimeLimeTasksByDate(date: Int): List<TimeLineTask>
}