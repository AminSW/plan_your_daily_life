package com.example.planyourlife

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarScreen() {
    val currentMonth = remember { YearMonth.now() }
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfMonth = currentMonth.atDay(1).dayOfWeek.value % 7 // Adjust for Sunday = 0
    val today = LocalDate.now().dayOfMonth

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Month Title
            Text(
                text = "${currentMonth.month} ${currentMonth.year}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Day headers
            Row(modifier = Modifier.fillMaxWidth()) {
                listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach { day ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = day, fontWeight = FontWeight.SemiBold, color = Color.Gray)
                    }
                }
            }

            // Calendar Grid
            Column(modifier = Modifier.fillMaxWidth()) {
                val totalCells = firstDayOfMonth + daysInMonth
                val rows = totalCells / 7 + if (totalCells % 7 > 0) 1 else 0
                var dayCounter = 1

                for (i in 0 until rows) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        for (j in 0..6) {
                            if ((i == 0 && j < firstDayOfMonth) || dayCounter > daysInMonth) {
                                // Empty box for padding
                                Box(modifier = Modifier.weight(1f).padding(4.dp))
                            } else {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                        .padding(4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "$dayCounter",
                                        fontSize = 16.sp,
                                        color = if (dayCounter == today) Color.Blue else Color.Black,
                                        textAlign = TextAlign.Center
                                    )
                                    dayCounter++
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
