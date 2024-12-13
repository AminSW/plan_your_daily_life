package com.example.planyourlife.ContentOfTask

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planyourlife.Tasks.TaskItem
import com.example.planyourlife.Tasks.TaskOfPlan

@Composable
fun ExerciseTableScreen()
{
    // Checkbox durumlarını yönetmek için bir liste
    val exerciseList = remember {
        mutableStateListOf(
            Exercise("product 1", 2, false),
            Exercise("product 2", 2, false),
            Exercise("product 3", 2, false),
            Exercise("product 4", 2, false),
        )
    }

    val workout = Workout(exerciseList = exerciseList, id = 1, name = "shopping")
    workout.ShowTaskContent()

}

class Workout(
    val exerciseList: MutableList<Exercise>, id: Int, name: String
): TaskOfPlan(id, name)
{

    @Composable
    override fun ShowTaskContent()
    {
        WorkoutListScreen(exerciseList = exerciseList)
    }

}

class Exercise(val name: String, private val number: Int, isCompleted: Boolean) : TaskItem() {
    // Durumu MutableState olarak tanımlayın
    private var isCompleted by mutableStateOf(isCompleted)
        private set // Dışarıdan doğrudan değiştirilemez

    // Durumu güncelleyen fonksiyon
    private fun updateCompletionStatus() {
        isCompleted = !isCompleted
    }

    @Composable
    override fun showDetails() {
        ExerciseListRow(
            name = name,
            number = number,
            isChecked = isCompleted,
            onCheckedChange = {
                updateCompletionStatus() // Durumu burada güncelleriz
            }
        )
    }
}


@Composable
fun WorkoutListScreen(exerciseList: MutableList<Exercise>)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tablo Başlığı
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Exercise list",
                modifier = Modifier.weight(2f),
                style = MaterialTheme.typography.bodySmall
            )

        }
        // Egzersiz Satırları
        exerciseList.forEachIndexed { _, exercise ->
            exercise.showDetails()
        }
    }
}


@Composable
fun ExerciseListRow(
    name: String,
    number: Int,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = Color(0xFFBBDEFB), // Soft mavi renk
                shape = RoundedCornerShape(16.dp) // Yuvarlatılmış köşeler
            )
            .shadow(
                elevation = 0.dp, // Hafif gölge
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp), // İçerik için padding
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox
        Checkbox(
            checked = isChecked,
            onCheckedChange = { onCheckedChange(it) },
            modifier = Modifier.padding(end = 16.dp)
        )

        // Egzersiz Adı
        Text(
            text = name,
            modifier = Modifier.weight(2f),
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp)
        )

        // Sayısı
        Text(
            text = "x $number",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp)
        )
    }
}
