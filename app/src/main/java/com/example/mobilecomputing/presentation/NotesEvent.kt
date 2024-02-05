package com.example.mobilecomputing.presentation

import com.example.mobilecomputing.data.Note

sealed interface NotesEvent {

    data class SaveProfile(
        val title: String,
    ): NotesEvent
}