package com.example.calendartracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calendartracker.ui.theme.CalendarTrackerTheme
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        NotificationHelper.createNotificationChannel(this) // Creates the notification channel
        setContent {
            // Creates the theme
            var isDarkMode by remember { mutableStateOf(false) }

            CalendarTrackerTheme(darkTheme = isDarkMode) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var currentScreen by remember { mutableStateOf(Screen.Main) }

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Toggle Button for Light/Dark Mode
                        Button(onClick = { isDarkMode = !isDarkMode }) {
                            Text(if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode")
                        }

                        // Main content screens
                        when (currentScreen) {
                            Screen.Main -> MainContent(
                                onDismiss = { currentScreen = Screen.Day },
                                onNotify = { scheduleNotification() },
                                modifier = Modifier.padding(innerPadding)
                            )
                            Screen.Day -> DayViewScreen(
                                onBack = { currentScreen = Screen.Main },
                                onWeekView = { currentScreen = Screen.Week },
                                onMonthView = { currentScreen = Screen.Month }
                            )
                            Screen.Week -> WeekViewScreen(onBack = { currentScreen = Screen.Day })
                            Screen.Month -> MonthViewScreen(onBack = { currentScreen = Screen.Day })
                        }
                    }
                }
            }
        }
    }

    private fun scheduleNotification() {
        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>().build()
        WorkManager.getInstance(this).enqueue(workRequest)

        //Creates notification
        Toast.makeText(this, "Notification scheduled!", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    CalendarTrackerTheme {
        MainContent(onDismiss = {}, onNotify = {})
    }
}

enum class Screen {
    Main,
    Day,
    Week,
    Month
}
