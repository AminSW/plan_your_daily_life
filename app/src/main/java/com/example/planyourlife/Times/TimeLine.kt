package com.example.planyourlife.Times

class TimeLine(private val start: Float, private val end: Float)
{
    fun returnDuration(): ArrayList<Float> {
        val duration = ArrayList<Float>()
        var index = start

        while (index <= end)
        {
            if(index == end) break
            duration.add(index)
            index += 0.5f
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