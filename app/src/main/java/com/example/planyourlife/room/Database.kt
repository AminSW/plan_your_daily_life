package com.example.planyourlife.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.planyourlife.data.entity.TimeLineTask

@Database(entities = [TimeLineTask::class], version = 1)
abstract class Database: RoomDatabase()
{
    abstract fun getTimeLineTaskDAO(): TimeLineTaskDAO
}