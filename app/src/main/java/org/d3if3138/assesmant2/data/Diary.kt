package org.d3if3138.assesmant2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diary(
    val title: String,
    val description: String,
    val dateAdded: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)