package com.example.planyourlife.ViewOfDailyScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planyourlife.StructureOfViews.DailyTimeLine
import com.example.planyourlife.Tasks.TaskOfPlanTL
import com.example.planyourlife.ui.theme.mainColor
import kotlin.math.roundToInt

@Composable
fun TimelineScreen(timeLine: DailyTimeLine) {

    val list = timeLine.getTasks()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top
    )
    {
        itemsIndexed(list) { index, event ->
            val previousEvent = if (index > 0) list[index - 1] else null
            TimelineItem(event, previousEvent)
        }
    }
}

@Composable
fun TimelineItem(event: TaskOfPlanTL, previousEvent: TaskOfPlanTL?)
{
    var previousEndTime = -1.0f
    if(previousEvent != null) previousEndTime = previousEvent.getTimeline().getEndTime()

    val startTimeFloat = event.getTimeline().getStartTime()
    val endTimeFloat = event.getTimeline().getEndTime()
    val startTime = startTimeFloat.toTimeString()
    val endTime = endTimeFloat.toTimeString()

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        if(previousEndTime != startTimeFloat)
        {
            Row {
                Spacer(modifier = Modifier.size(20.dp))
                Box(
                    modifier = Modifier
                        .width(15.dp)
                        .height(20.dp)
                        .background(mainColor)
                )
            }

            CircularText(text = startTime)
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Spacer(modifier = Modifier.size(20.dp))

            Box(
                modifier = Modifier
                    .width(15.dp)
                    .height(90.dp)
                    .background(mainColor)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(start = 18.dp)
                    .background(
                        color = Color(0xFFFF9800),
                        shape = RoundedCornerShape(12.dp)
                    )
                ,
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = event.name,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

        CircularText(text = endTime)
    }
}

@Composable
fun CircularText(text: String)
{
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(40.dp)
            .width(60.dp)
            .background(
                mainColor,
                shape = androidx.compose.foundation.shape.CircleShape // Yuvarlak şekil
            )
    )
    {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

fun Float.toTimeString(): String {
    val hours = this.toInt()
    val minutes = ((this - hours) * 60).roundToInt()
    val adjustedHours = hours + minutes / 60
    val adjustedMinutes = minutes % 60

    return "%02d:%02d".format(adjustedHours, adjustedMinutes)
}
