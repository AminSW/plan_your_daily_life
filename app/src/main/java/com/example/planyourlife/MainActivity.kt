package com.example.planyourlife

import CalendarApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.planyourlife.ui.theme.PlanYourLifeTheme
import com.example.planyourlife.view.viewmodels.CalendarViewModel
import com.example.planyourlife.view.viewmodels.DailyPlanningViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val calendarViewModel:CalendarViewModel by viewModels ()
    private val dailyPlanningViewModel:DailyPlanningViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanYourLifeTheme {

                CalendarApp(
                    calendarViewModel = calendarViewModel,
                    dailyPlanningViewModel = dailyPlanningViewModel
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlanYourLifeTheme {
        Greeting("Android")
    }
}

