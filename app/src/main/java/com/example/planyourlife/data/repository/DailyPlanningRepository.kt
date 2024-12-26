package com.example.planyourlife.data.repository

import com.example.planyourlife.data.datasource.DailyPlanningDataSource
import com.example.planyourlife.data.entity.Day

class DailyPlanningRepository
{
    private val datasource = DailyPlanningDataSource()

    suspend fun createDays(): HashMap<String, Day> = datasource.createDays()

}