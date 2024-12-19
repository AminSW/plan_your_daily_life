package com.example.planyourlife.Tasks

import com.example.planyourlife.Times.TimeLine

open class TaskOfPlanTL(id: Int, name: String, private val timeLine: TimeLine) : TaskOfPlan(id, name), TaskWithTimeLine
{
    override fun getTimeline(): TimeLine {
        return timeLine
    }

}