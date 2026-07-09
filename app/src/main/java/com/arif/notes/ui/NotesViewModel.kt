package com.arif.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arif.notes.data.Note
import com.arif.notes.data.NoteDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class NotesViewModel(private val dao: NoteDao) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val notes: StateFlow<List<Note>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) dao.getAllNotes() else dao.searchNotes(query)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setSearch(query: String) { _searchQuery.value = query }

    fun addOrUpdateNote(title: String, content: String, editing: Note?) {
        if (title.isBlank() && content.isBlank()) return
        viewModelScope.launch {
            val note = if (editing != null) {
                editing.copy(title = title.ifBlank { "Untitled" }, content = content, timestamp = System.currentTimeMillis())
            } else {
                Note(title = title.ifBlank { "Untitled" }, content = content)
            }
            dao.insert(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch { dao.delete(note) }
    }
}
