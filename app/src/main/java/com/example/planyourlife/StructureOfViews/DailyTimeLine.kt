package com.example.planyourlife.StructureOfViews

import com.example.planyourlife.Tasks.TaskOfPlan
import com.example.planyourlife.Tasks.TaskOfPlanTL
import com.example.planyourlife.Tasks.TaskWithTimeLine
import com.example.planyourlife.Times.Time
import com.example.planyourlife.Times.TimeLine

class DailyTimeLine
{

    private val timeLineList = ArrayList<TaskOfPlanTL>()
    private val timeLineSet = HashSet<Float>()

    fun addTimeLine(task: TaskOfPlanTL)
    {
        val timeLine = task.getTimeline()
        if(controlAddingTimeLine(timeLine))
        {
            timeLineSet.addAll(timeLine.returnDuration())
            addingTimeLineInOrder(task)
        }
    }

    fun getTasks(): List<TaskOfPlanTL> {
        return timeLineList
    }

    private fun addingTimeLineInOrder(task: TaskOfPlanTL)
    {
        val timeLineStartValue = task.getTimeline().getStartTime()

        var index = 0
        val length = timeLineList.size
        if(length == 0) return timeLineList.add(index, task)
        while(index < length)
        {
            if(timeLineList[index].getTimeline().getStartTime() >= timeLineStartValue)
                return timeLineList.add(index, task)
            index++
        }
    }

    private fun controlAddingTimeLine(timeLine: TimeLine): Boolean
    {
        val duration = timeLine.returnDuration()
        for(time in duration)
        {
            if(timeLineSet.contains(time)) return false
        }
        return true
    }


}

