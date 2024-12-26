package com.example.planyourlife.data.datasource

import androidx.compose.runtime.mutableStateListOf
import com.example.planyourlife.ContentOfTask.Exercise
import com.example.planyourlife.ContentOfTask.Shopping
import com.example.planyourlife.ContentOfTask.ShoppingItem
import com.example.planyourlife.ContentOfTask.Workout
import com.example.planyourlife.StructureOfViews.DailyTimeLine
import com.example.planyourlife.Tasks.TaskItem
import com.example.planyourlife.Tasks.TaskOfPlanTL
import com.example.planyourlife.Times.TimeLine
import com.example.planyourlife.data.entity.Day
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class DailyPlanningDataSource
{
    suspend fun createDays(): HashMap<String, Day> = withContext(Dispatchers.IO) {

        val exercise = createWorkout()
        val shopping = createShoppingTask()

        val dailyTimeLine = createTimeLineOfDay()

        val days = hashMapOf<String, Day>(
            "2024-12-26" to Day(
                date = LocalDate.of(2024, 12, 26),
                tasks = mutableListOf(
                    shopping,
                    exercise
                ),
                dailyTimeLine = dailyTimeLine
            ),
            "2024-12-27" to Day(
                date = LocalDate.of(2024, 12, 27),
                tasks = mutableListOf(
                    exercise
                )
            )
        )

        return@withContext days
    }

    private suspend fun createWorkout(): Workout = withContext(Dispatchers.IO)
    {
        // Checkbox durumlarını yönetmek için bir liste
        val exerciseList =
            mutableStateListOf(
                Exercise("exercise 1", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
                Exercise("product 2", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
                Exercise("product 3", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
                Exercise("product 4", 2, exerciseWithTime = false, isCompleted = false) as TaskItem,
            )


        val workout = Workout(exerciseList = exerciseList, id = 1, name = "shopping", timeline = com.example.planyourlife.Times.TimeLine(10f, 12f))
        return@withContext workout
    }


    private suspend fun createShoppingTask(): Shopping = withContext(Dispatchers.IO) {
        val shoppingList =
            mutableStateListOf(
                ShoppingItem("product 1", 2, false) as TaskItem,
                ShoppingItem("product 2", 2, false) as TaskItem,
                ShoppingItem("product 3", 2, false) as TaskItem,
                ShoppingItem("product 4", 2, false) as TaskItem,
            )

        val shopping = Shopping(shoppingList = shoppingList, id = 1, name = "shopping")

        return@withContext shopping
    }

    private suspend fun createTimeLineOfDay(): DailyTimeLine = withContext(Dispatchers.IO) {
        val dailyTimeLine = DailyTimeLine()

        val exerciseList = mutableListOf(
            Exercise("exercise 1", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
            Exercise("exercise 2", 2, exerciseWithTime = true, isCompleted = false) as TaskItem,
            Exercise("exercise 3", 2, exerciseWithTime = true, isCompleted = false) as TaskItem
        )

        val exerciseList2 = mutableListOf(
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
        return@withContext dailyTimeLine
    }

}