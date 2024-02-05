package com.example.mobilecomputing.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(

    val title: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)