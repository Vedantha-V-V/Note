package com.example.note

import android.app.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteScreen(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        onDismissRequest = {
            onEvent(NoteEvent.HideNote)
        },
        confirmButton = TODO(),
        modifier = modifier,
        dismissButton = {
            Box(modifier =  modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd){
                Button(onClick = {
                    onEvent(NoteEvent.UpdateNote)
                }){
                    Text(text="Update")
                }
            }
        },
        icon = TODO(),
        title = { Text(text = "Add Note") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.title,
                    onValueChange = {
                        onEvent(NoteEvent.SetTitle(it))
                    },
                    placeholder = { Text(text = "Note Title") }
                )
            }
        }
    )
}