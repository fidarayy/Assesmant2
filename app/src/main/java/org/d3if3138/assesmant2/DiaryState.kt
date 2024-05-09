package org.d3if3138.assesmant2

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import org.d3if3138.assesmant2.data.Diary

data class DiaryState(
    val diarys: List<Diary> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf("")
) {
}