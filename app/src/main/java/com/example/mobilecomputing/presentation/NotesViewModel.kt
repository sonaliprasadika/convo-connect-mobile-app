package com.example.mobilecomputing.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilecomputing.data.Note
import com.example.mobilecomputing.data.NoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val dao: NoteDao
) : ViewModel() {


    private var notes = dao.getNotesOrderdByTitle()

    val _state = MutableStateFlow(NoteState())
    val state =
        combine(_state, notes) { state, notes ->
            state.copy(
                notes = notes
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())

    fun onEvent(event: NotesEvent) {
        when (event) {
//            is NotesEvent.DeleteNote -> {
//                viewModelScope.launch {
//                    dao.deleteNote(event.note)
//                }
//            }
//
//            is NotesEvent.SaveNote -> {
//                val note = Note(
//                    title = state.value.title.value,
//                    description = state.value.description.value,
//                    dateAdded = System.currentTimeMillis()
//                )
//
//                viewModelScope.launch {
//                    dao.upsertNote(note)
//                }
//
//                _state.update {
//                    it.copy(
//                        title = mutableStateOf(""),
//                        description = mutableStateOf("")
//                    )
//                }
//            }

            is NotesEvent.SaveProfile -> {
                val note = Note(
                    id = 1,
                    title = state.value.title.value,
                )

                viewModelScope.launch {
                    dao.upsertNote(note)
                }

                _state.update {
                    it.copy(
                        title = mutableStateOf("")
                    )
                }
            }

//            NotesEvent.SortNotes -> {
//                isSortedByDateAdded.value = !isSortedByDateAdded.value
//            }
            else -> {}
        }
    }

}