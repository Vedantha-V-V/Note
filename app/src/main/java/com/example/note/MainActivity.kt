package com.example.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.example.note.ui.theme.NoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier=Modifier.background(Color.hsl(41F, 0.48F, 0.7F)).fillMaxSize()) {
                        Heading(LocalContext.current,
                            name = "ReadiNote",
                            modifier = Modifier.padding(innerPadding)
                        )
                        Card {
                            Text("Hello")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Heading(context: Context,name: String, modifier: Modifier = Modifier) {
    Row(modifier=Modifier.fillMaxWidth()
        .padding(0.dp,20.dp,0.dp,10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = name,
            modifier = modifier
        )
        OutlinedButton(onClick = {
            context.startActivity(Intent(context, NoteActivity::class.java))
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
fun NoteSection(){
    
}