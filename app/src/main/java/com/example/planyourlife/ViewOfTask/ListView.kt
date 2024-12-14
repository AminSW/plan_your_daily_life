package com.example.planyourlife.ViewOfTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.planyourlife.ContentOfTask.ShoppingItem
import com.example.planyourlife.Tasks.TaskItem

@Composable
fun TaskListScreen(name: String, taskList: MutableList<TaskItem>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name, //"Shopping list",
                modifier = Modifier.weight(2f),
                style = MaterialTheme.typography.bodySmall
            )
        }

        taskList.forEach { taskItem ->
            taskItem.showDetails()
        }
    }
}