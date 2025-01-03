package com.example.planyourlife.view.dataStructures

import com.example.planyourlife.data.entity.TimeLineTask

class DailyTimeLineTasks {

    val timeLineList = ArrayList<TimeLineTask>()
    private val timeLineSet = HashSet<Int>() // Mutable Set

    fun addTimeLine(task: TimeLineTask) {
        getTaskDuration(task)?.let {
            timeLineSet.addAll(it) // Use addAll to add elements to the mutable set
            addingTimeLineInOrder(task)
        }
    }

    private fun getTaskDuration(task: TimeLineTask): HashSet<Int>? {
        val newTimeLineSet = timeLineSet.clone() as HashSet<Int>
        var index = task.startTime
        val end = task.endTime

                while (index <= end) {
                    if (index == end) break
                    if (newTimeLineSet.contains(index)) index += 5
                    else return null
                }

        return newTimeLineSet
    }

    private fun addingTimeLineInOrder(task: TimeLineTask) {
        val start = task.startTime

        var index = 0
        val length = timeLineList.size
        if (length == 0) return timeLineList.add(index, task)

        while(index < length)
        {
            if(timeLineList[index].startTime >= start)
                break
            index++
        }
        timeLineList.add(index, task)
    }
}