package com.example.calendartracker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainContent(onDismiss: () -> Unit, onNotify: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Here are your upcoming events",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 24.dp, bottom = 32.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Event 1: Meeting at 10 AM", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Event 2: Lunch with Sarah at 1 PM", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Event 3: Project deadline at 5 PM", style = MaterialTheme.typography.bodyLarge)
        }

        // Notification Button
        Button(
            onClick = onNotify,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(text = "Set Notification")
        }

        // Dismiss Button
        Button(
            onClick = onDismiss,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Text(text = "Dismiss")
        }
    }
}
