package com.example.note

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.note.ui.theme.Green80
import com.example.note.viewmodel.NoteViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddNoteScreen(
    showDialog: Boolean,
    viewModel: NoteViewModel,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
){
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    AlertDialog(onDismissRequest = onDismiss,
        containerColor = Color(0xFFFFE1AF),
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { newText -> title = newText },
                    //label = { Text("Title") },
                    placeholder = { Text("Title") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color(0xFF895129),
                        unfocusedTextColor = Color(0xFF895129),
                    )
                )
                TextField(
                    value = content,
                    onValueChange = { newText -> content = newText },
                    placeholder = { Text("Start Writing") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth() ,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color(0xFF895129),
                        unfocusedTextColor = Color(0xFF895129),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    )
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                viewModel.addNote(title,content)
                onConfirm(title) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB77466)
                )) {
                Icon(Icons.Filled.Done,"Save Button",  tint = Green80)
            }
        },
        dismissButton = {
            Button(onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB77466)
                )) {
                Icon(Icons.Filled.Close,"Close Button",  tint = Green80)
            }
        })
}

