package com.example.planyourlife.data.entity

import com.example.planyourlife.StructureOfViews.DailyTimeLine
import com.example.planyourlife.Tasks.TaskOfPlan
import com.example.planyourlife.Tasks.TaskOfPlanTL
import java.time.LocalDate

data class Day(
    val date: LocalDate,
    val tasks: MutableList<TaskOfPlan>,
    val dailyTimeLine: DailyTimeLine = DailyTimeLine()
)