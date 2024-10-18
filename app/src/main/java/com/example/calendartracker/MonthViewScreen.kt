package com.example.calendartracker
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.util.Calendar
import java.util.Locale

@SuppressLint("AutoboxingStateCreation")
@Composable
fun MonthViewScreen(onBack: () -> Unit) {
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    var showDialog by remember { mutableStateOf(false) }
    var selectedDayEvents by remember { mutableStateOf<List<String>>(emptyList()) }
    var selectedDay by remember { mutableStateOf(0) }

    // Events for the month
    val eventsForMonth = mapOf(
        1 to listOf("Meeting at 10 AM"),
        3 to listOf("Project deadline at 5 PM"),
        5 to listOf("Team Outing at 5 PM"),
        10 to listOf("Lunch with Sarah"),
        15 to listOf("Client meeting")
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "${calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())} $currentYear",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // Display the days in a grid
        Column {
            // Header for the days of the week
            Row(modifier = Modifier.fillMaxWidth()) {
                val daysOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
                daysOfWeek.forEach { day ->
                    Text(text = day, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
                }
            }

            // Shows days in the month
            for (week in 0 until 6) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (day in 1..7) {
                        val dayOfMonth = week * 7 + day - (firstDayOfWeek - 1)
                        if (dayOfMonth in 1..daysInMonth) {
                            val hasEvent = eventsForMonth.containsKey(dayOfMonth)
                            val isToday = dayOfMonth == calendar.get(Calendar.DAY_OF_MONTH)

                            // Day box
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                                    .background(if (isToday) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f) else Color.Transparent)
                                    .clickable {
                                        if (hasEvent) {
                                            selectedDayEvents = eventsForMonth[dayOfMonth] ?: emptyList()
                                            selectedDay = dayOfMonth
                                            showDialog = true
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = dayOfMonth.toString(), style = MaterialTheme.typography.bodyLarge)
                                    if (hasEvent) {
                                        Text(text = "${eventsForMonth[dayOfMonth]?.size} event(s)", style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                            }
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack) {
            Text(text = "Back")
        }
    }

    // Show event details
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Events for $selectedDay") },
            text = {
                Column {
                    selectedDayEvents.forEach { event ->
                        Text(text = event)
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}