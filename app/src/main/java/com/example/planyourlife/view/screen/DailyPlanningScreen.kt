import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.planyourlife.ContentOfTask.Exercise
import com.example.planyourlife.ContentOfTask.Workout
import com.example.planyourlife.StructureOfViews.DailyTimeLine
import com.example.planyourlife.Tasks.TaskItem
import com.example.planyourlife.Tasks.TaskOfPlanTL
import com.example.planyourlife.Times.TimeLine
import com.example.planyourlife.ViewOfDailyScreen.DaySelector
import com.example.planyourlife.ViewOfDailyScreen.TimelineScreen
import com.example.planyourlife.data.entity.Day
import com.example.planyourlife.view.viewmodels.DailyPlanningViewModel
import java.time.LocalDate

@Composable
fun DailyPlanningScreen(viewModel: DailyPlanningViewModel, date: String?)
{
    val days = viewModel.days.observeAsState().value ?: emptyMap()
    println("control - ${days.keys}")
    println("control - $date")
    val selectedDay = days[date]

    var dayOfMonth = 1
    var monthOfYear = 1
    if(date != null){
        dayOfMonth = getDayOfMonth(date)
        monthOfYear = getMonthOfYear(date)
    }

    val lastDayOfMonth = lastDayOfMonths[monthOfYear-1]

    selectedDay?.let { DailyScheduleScreen(day = dayOfMonth, lastDayOfMonth = lastDayOfMonth, selectedDay = it) }

}


fun getDayOfMonth(dateString: String): Int {
    val date = LocalDate.parse(dateString)
    return date.dayOfMonth
}

fun getMonthOfYear(dateString: String): Int {
    val date = LocalDate.parse(dateString)
    return date.monthValue
}

val lastDayOfMonths = intArrayOf(31,28,31,30,31,30,31,31,30,31,30,31)

@Composable
fun DailyScheduleScreen(
    day : Int,
    lastDayOfMonth : Int,
    selectedDay : Day
)
{
    var dayOfTask: Int by remember { mutableIntStateOf(day) }

    // UI düzeni
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,

    ) {
        
        Spacer(modifier = Modifier.height(16.dp))
        DaySelector(
            selectedDay = day,
            lastDayOfMonth = dayOfTask,
            onDaySelected = { newDay ->
                dayOfTask = newDay // Seçilen günü güncelle
            }
        )

        TimelineScreen(selectedDay.dailyTimeLine)
    }
}


fun getTimeLineTask(): DailyTimeLine
{
    val dailyTimeLine = DailyTimeLine()

    val exerciseList = mutableListOf<TaskItem>(
        Exercise("exercise 1", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
        Exercise("exercise 2", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
        Exercise("exercise 3", 2, exerciseWithTime = true, isCompleted = false) as TaskItem
    )

    val exerciseList2 = mutableListOf<TaskItem>(
        Exercise("exercise 1", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
        Exercise("exercise 2", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
        Exercise("exercise 3", 2, exerciseWithTime = true, isCompleted = false) as TaskItem
    )

    dailyTimeLine.addTimeLine(
        Workout(
            exerciseList = exerciseList,
            id = 1,
            name = "workout 1",
            timeline = TimeLine(10f, 12f)
        ) as TaskOfPlanTL
    )

    dailyTimeLine.addTimeLine(
        Workout(
            exerciseList = exerciseList2,
            id = 2,
            name = "workout 2",
            timeline = TimeLine(16f, 20f)
        ) as TaskOfPlanTL
    )

    dailyTimeLine.addTimeLine(
        Workout(
            exerciseList = exerciseList2,
            id = 2,
            name = "workout 2",
            timeline = TimeLine(12f, 14f)
        ) as TaskOfPlanTL
    )
    //println("bbb - ${dailyTimeLine.getTasks().get(0).getTimeline().getStartTime()}")
    return dailyTimeLine
}








