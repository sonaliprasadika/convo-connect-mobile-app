package com.example.mobilecomputing.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.mobilecomputing.data.Note

data class NoteState(

    val notes: List<Note> = emptyList(),
//    val notes: List<Note> = listOf(Note("sonali")),
    val title: MutableState<String> = mutableStateOf(""),
//    val description: MutableState<String> = mutableStateOf("")
    val description: Array<String> = arrayOf("Nile", "Amazon","mahavali"),

    val profileImage: MutableState<String> = mutableStateOf("https://4.img-dpreview.com/files/p/E~TS1180x0~articles/3925134721/0266554465.jpeg"),

    )