package org.d3if3138.assesmant2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.d3if3138.assesmant2.data.Diary
import org.d3if3138.assesmant2.data.DiaryDao

class DiaryViewModel(private val dao: DiaryDao): ViewModel() {
    private  val isSortedByDateAdded = MutableStateFlow(true)
    private var diarys = isSortedByDateAdded.flatMapLatest { sort ->
        if (sort) {
            dao.getDiarysOrderByDateAdded()
        } else {
            dao.getDiarysOrderByTitle()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(DiaryState())
    val state = combine(_state,isSortedByDateAdded, diarys) {state, isSortedByDateAdded, diarys ->
        state.copy(diarys = diarys)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DiaryState())

    fun onEvent(event: DiaryEvent) {
        when (event) {
            is  DiaryEvent.DeleteDiary -> {
                viewModelScope.launch {
                    dao.deleteDiary(event.diary)
                }
            }

            is DiaryEvent.SeveDiary -> {
                val diary = Diary(
                    title = state.value.title.value,
                    description = state.value.description.value,
                    dateAdded = System.currentTimeMillis()
                )
                viewModelScope.launch { dao.upsertDiary(diary)
                }
                _state.update { it.copy(
                    title = mutableStateOf(""),
                    description = mutableStateOf("")
                )
                }
            }
            DiaryEvent.SortDiary -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }

            is DiaryEvent.OpenDiary -> TODO()
        }
    }
}