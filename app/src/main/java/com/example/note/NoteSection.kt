package com.example.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.viewmodel.NoteViewModel

@Composable
fun NoteSection(viewModel: NoteViewModel){

    val notes by viewModel.notes.observeAsState()

    notes?.let{
        LazyVerticalGrid(
            columns= GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notes as List<Any?>){ item ->
                NoteUI(item = item as Note, onDelete = {
                    viewModel.deleteNote(item.id)
                })
            }
        }
    }?:Text(
        modifier=Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "No notes",
        fontSize = 16.sp
    )


}

@Composable
fun NoteUI(item : Note,onDelete : ()-> Unit,modifier:Modifier=Modifier){
    Card(modifier=modifier.fillMaxWidth()
        .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)){
        Text(text=item.title,modifier=Modifier.padding(16.dp))
        Text(text=item.content,modifier=Modifier.padding(16.dp))
        IconButton(onClick = onDelete){
            Icon(imageVector=Icons.Default.Delete,contentDescription="Delete Note")
        }
    }
}