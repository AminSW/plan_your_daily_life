package com.example.planyourlife.Times

class TimeLine(private val start: Float, private val end: Float)
{
    fun returnDuration(): ArrayList<Float> {
        val duration = ArrayList<Float>()
        var index = start

        while (index <= end)
        {
            duration.add(index)
            index++
        }

        return duration
    }

    fun getStartTime(): Float
    {
        return start
    }

    fun getEndTime(): Float
    {
        return end
    }
}