package com.example.planyourlife.data.repository

import com.example.planyourlife.data.datasource.DailyPlanningDataSource
import com.example.planyourlife.data.entity.Day

class DailyPlanningRepository(var dataSource: DailyPlanningDataSource)
{

    suspend fun createDays(): HashMap<String, Day> = dataSource.createDays()

}