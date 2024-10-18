package com.example.calendartracker
import android.widget.CalendarView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DayViewScreen(onBack: () -> Unit, onWeekView: () -> Unit, onMonthView: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Home", modifier = Modifier.padding(bottom = 16.dp))
        Text(text = "Here you can view the details of your day.")
        Spacer(modifier = Modifier.height(16.dp))

        // Add Calendar
        AndroidView(factory = { context ->
            CalendarView(context).apply {

            }
        }, modifier = Modifier.fillMaxWidth().height(300.dp))

        Spacer(modifier = Modifier.height(16.dp))
        //Creates a button to navigate to the week view
        Button(onClick = onWeekView) {
            Text(text = "Week View")
        }
        //Creates a button to navigate to the month view
        Button(onClick = onMonthView) {
            Text(text = "Month View")
        }
        //Creates a button to navigate back to the main screen
        Button(onClick = onBack) {
            Text(text = "Back")
        }
    }
}