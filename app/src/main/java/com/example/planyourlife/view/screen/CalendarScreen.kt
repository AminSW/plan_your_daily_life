import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planyourlife.ContentOfTask.Exercise
import com.example.planyourlife.ContentOfTask.Shopping
import com.example.planyourlife.ContentOfTask.ShoppingItem
import com.example.planyourlife.ContentOfTask.Workout
import com.example.planyourlife.Day
import com.example.planyourlife.Tasks.TaskItem
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun createExerciseTask(): Workout {
    // Checkbox durumlarını yönetmek için bir liste
    val exerciseList = remember {
        mutableStateListOf(
            Exercise("exercise 1", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
            Exercise("product 2", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
            Exercise("product 3", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
            Exercise("product 4", 2, exerciseWithTime = false, isCompleted = false) as TaskItem,
        )
    }

    val workout = Workout(exerciseList = exerciseList, id = 1, name = "shopping", timeline = com.example.planyourlife.Times.TimeLine(10f, 12f))
    return workout

}

@Composable
fun createShoppingTask(): Shopping {
    val shoppingList = remember {
        mutableStateListOf(
            ShoppingItem("product 1", 2, false) as TaskItem,
            ShoppingItem("product 2", 2, false) as TaskItem,
            ShoppingItem("product 3", 2, false) as TaskItem,
            ShoppingItem("product 4", 2, false) as TaskItem,
        )
    }
    val shopping = Shopping(shoppingList = shoppingList, id = 1, name = "shopping")

    return shopping
    //shopping.ShowTaskContent()
}

@Composable
fun createDays(): HashMap<String, Day> {

    val exercise = createExerciseTask()
    val shopping = createShoppingTask()


    val days = hashMapOf<String, Day>(
        "2024-12-14" to Day(
            date = LocalDate.of(2024, 12, 14),
            tasks = mutableListOf(
                //shopping,
                exercise
            )
        ),
        "2024-12-15" to Day(
            date = LocalDate.of(2024, 12, 15),
            tasks = mutableListOf(
                exercise
            )
        )
    )

    return days
}

@Composable
fun CalendarApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "calendar") {
        composable("calendar") {
            CalendarScreen(onDayClick = { date ->
                navController.navigate("details/${date}")
            })
        }
        composable("details/{date}") { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date")
            DayDetailsScreen(date = date)
        }
    }
}

@Composable
fun CalendarScreen(onDayClick: (String) -> Unit) {
    val currentMonth = YearMonth.now()
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfMonth = currentMonth.atDay(1).dayOfWeek
    val today = LocalDate.now()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Month and Year Header
        Text(
            text = "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Days of Week Row
        val daysOfWeek = DayOfWeek.values().map {
            it.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            daysOfWeek.forEach { day ->
                Text(
                    text = day,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Days Grid
        val totalCells = (daysInMonth + firstDayOfMonth.value - 1) // Total slots in grid
        val rows = (totalCells / 7) + 1 // Calculate number of rows needed

        Column {
            var dayCounter = 1
            for (rowIndex in 0 until rows) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (colIndex in 0 until 7) {
                        val day = if (rowIndex == 0 && colIndex < firstDayOfMonth.value - 1 ||
                            dayCounter > daysInMonth
                        ) null else dayCounter++

                        DayCell(
                            day = day,
                            isToday = today.dayOfMonth == day && today.month == currentMonth.month,
                            onClick = {
                                day?.let {
                                    val selectedDate = LocalDate.of(currentMonth.year, currentMonth.month, it)
                                    onDayClick(selectedDate.toString())
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DayCell(day: Int?, isToday: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .padding(4.dp)
            .background(
                color = when {
                    day == null -> Color.Transparent
                    isToday -> MaterialTheme.colorScheme.primary
                    else -> Color.White
                },
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(enabled = day != null) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day?.toString() ?: "",
            fontSize = 16.sp,
            color = if (isToday) Color.White else Color.Black,
            textAlign = TextAlign.Center
        )
    }
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
fun DayDetailsScreen(date: String?)
{
    val days = createDays()
    val selectedDay = days[date]

    var dayOfMonth = 1
    var monthOfYear = 1
    if(date != null){
        dayOfMonth = getDayOfMonth(date)
        monthOfYear = getMonthOfYear(date)
    }

    val lastDayOfMonth = lastDayOfMonths[monthOfYear-1]
    
    DailyScheduleScreen(day = dayOfMonth, lastDayOfMonth = lastDayOfMonth)

}
