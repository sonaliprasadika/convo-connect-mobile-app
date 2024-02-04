package com.example.mobilecomputing.presentation

import com.example.mobilecomputing.data.Note

sealed interface NotesEvent {
    object SortNotes: NotesEvent

    data class DeleteNote(val note: Note): NotesEvent

    data class SaveNote(
        val title: String,
        val description: String
    ): NotesEvent

    data class SaveProfile(
        val title: String,
    ): NotesEvent
}