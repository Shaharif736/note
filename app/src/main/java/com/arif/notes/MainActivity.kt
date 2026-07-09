package com.arif.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.room.Room
import com.arif.notes.data.NoteDatabase
import com.arif.notes.ui.NotesApp
import com.arif.notes.ui.NotesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java, "arif_notes_db"
        ).build()

        val viewModel = NotesViewModel(db.noteDao())

        setContent {
            ArifNotesTheme {
                NotesApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun ArifNotesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = when {
        darkTheme -> darkColorScheme(
            primary = androidx.compose.ui.graphics.Color(0xFF8AB4F8),
            surface = androidx.compose.ui.graphics.Color(0xFF121212),
            surfaceVariant = androidx.compose.ui.graphics.Color(0xFF1E1E1E)
        )
        else -> lightColorScheme(
            primary = androidx.compose.ui.graphics.Color(0xFF1A73E8),
            surfaceVariant = androidx.compose.ui.graphics.Color(0xFFF1F3F4)
        )
    }
    MaterialTheme(colorScheme = colorScheme, content = content)
}
