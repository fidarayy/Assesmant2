package org.d3if3138.assesmant2

import org.d3if3138.assesmant2.data.Diary

sealed interface DiaryEvent {
   object SortDiary: DiaryEvent

    data class DeleteDiary(val diary: Diary): DiaryEvent

    data class SeveDiary(
        val title: String,
        val description: String
    ): DiaryEvent
    data class OpenDiary(val diary: Diary) : DiaryEvent
}