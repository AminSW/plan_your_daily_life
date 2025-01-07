package com.example.planyourlife.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TimeLineTask")
data class TimeLineTask (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "TaskID") val taskID: Int,
    @ColumnInfo(name = "Day") val date: Int,
    @ColumnInfo(name = "Name") val taskName: String,
    @ColumnInfo(name = "StartTime") val startTime: Int,
    @ColumnInfo(name = "EndTime") val endTime: Int
)


