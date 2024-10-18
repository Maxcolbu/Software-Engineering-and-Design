package com.example.calendartracker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun WeekViewScreen(onBack: () -> Unit) {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY) // Start the week on a Monday

    val eventsForWeek = mapOf(
        "Mon" to listOf("Team Meeting at 10 AM"),
        "Tue" to listOf("Project Review at 2 PM"),
        "Wed" to listOf("Lunch with Client at 12 PM"),
        "Thu" to listOf("Deadline for Reports"),
        "Fri" to listOf("Team Outing at 5 PM"),
        "Sat" to listOf("Weekend Plans"),
        "Sun" to listOf("Family Dinner")
    )

    var selectedDayEvents by remember { mutableStateOf<List<String>>(emptyList()) }
    var selectedDay by remember { mutableStateOf("") }

    // Shows all events for the week
    val summaryEvents = eventsForWeek.flatMap { (day, events) -> events.map { "$day: $it" } }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Week View", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp))

        // shows days of the week
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            for (i in 0 until 7) {
                val dayLabel = SimpleDateFormat("EEE", Locale.getDefault()).format(calendar.time)
                Text(
                    text = dayLabel,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            selectedDayEvents = eventsForWeek[dayLabel] ?: emptyList()
                            selectedDay = dayLabel
                        },
                    style = MaterialTheme.typography.bodyLarge
                )
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Shows events for the selected day
        if (selectedDay.isNotEmpty()) {
            Text(text = "Events for $selectedDay:", style = MaterialTheme.typography.headlineSmall)
            selectedDayEvents.forEach { event ->
                Text(text = event, style = MaterialTheme.typography.bodyMedium)
            }
        } else {
            Text(text = "Select a day to see events", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(text = "Summary of Events for the Week:", style = MaterialTheme.typography.headlineSmall)
        summaryEvents.forEach { summaryEvent ->
            Text(text = summaryEvent, style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack) {
            Text(text = "Back")
        }
    }
}