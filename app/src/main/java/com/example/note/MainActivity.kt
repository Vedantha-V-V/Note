package com.example.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.note.ui.theme.NoteTheme
import com.example.note.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            NotesDB::class.java,
            "notes.db"
        ).build()
    }

    private val viewModel by viewModels<NoteViewModel>(
        factoryProducer = {
            object: ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteViewModel(db.dao) as T
                }
            }

        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteTheme {
                //val state by viewModel
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier=Modifier.background(Color.hsl(41F, 0.48F, 0.7F)).fillMaxSize()) {
                        Heading(state = ,
                            name = "LemmiNote",
                            modifier = Modifier.padding(innerPadding)
                        )
                        NoteSection()
                    }
                }
            }
        }
    }
}

@Composable
fun Heading(state: NoteState,onEvent: (NoteEvent) -> Unit, context: Context, name: String, modifier: Modifier = Modifier) {
    Row(modifier=Modifier.fillMaxWidth()
        .padding(0.dp,20.dp,0.dp,10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = name,
            modifier = modifier
        )
        OutlinedButton(onClick = {
            onEvent(NoteEvent.AddNote(note=Note("Add Title","...")))
            //context.startActivity(Intent(context, NoteActivity::class.java))
        },
            modifier=Modifier.size(35.dp),
            shape= CircleShape,
            border=BorderStroke(2.dp, Color(0xFF895129)),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor=Color.Blue)
        ) {
            Icon(Icons.Filled.Add,"Add Button")
        }
    }
}

@Composable
fun NoteSection(state:NoteState, onEvent: (NoteEvent) -> Unit){
    val data = state.notes
    println(data)
    if(state.isNoting){
        NoteScreen()
    }
    LazyColumn(modifier=Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement=Arrangement.spacedBy(8.dp)){
        items(data.chunked(2).size){ index ->
            val rowItem = data.chunked(2)[index]
            Row(modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)){
                Card(modifier=Modifier.weight(1f)){
                   Text(text=rowItem[0].title,modifier=Modifier.padding(16.dp))
                    Text(text=rowItem[0].content,modifier=Modifier.padding(16.dp))
                    IconButton({
                        onEvent(NoteEvent.DeleteNote(rowItem[0]))
                    }){
                        Icon(imageVector=Icons.Default.Delete,contentDescription="Delete Note")
                    }
                }
                if(rowItem.size > 1){
                    Card(modifier=Modifier.weight(1f)){
//                        Text(text=rowItem[1],modifier=Modifier.padding(16.dp))
                    }
                }else{
                    Spacer(modifier=Modifier.weight(1f))
                }
            }
        }
    }
}