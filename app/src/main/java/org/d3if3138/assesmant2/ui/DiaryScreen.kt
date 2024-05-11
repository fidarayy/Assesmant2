package org.d3if3138.assesmant2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.d3if3138.assesmant2.DiaryEvent
import org.d3if3138.assesmant2.DiaryState
import org.d3if3138.assesmant2.R


@Composable
fun DiaryScreen(
    state: DiaryState,
    navController: NavController,
    onEvent: (DiaryEvent) -> Unit
) {

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.weight(1f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                IconButton(onClick = { onEvent(DiaryEvent.SortDiary) }) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "Diary View",
                        modifier = Modifier.size(35.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.title.value = ""
                state.description.value = ""
                navController.navigate("AddDiaryScreen")
            }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Tambah Diary"
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.diarys.size) { index ->
                DiaryItem(
                    state = state,
                    index = index,
                    onEvent = onEvent

                )
            }
        }
    }
}

@Composable
fun DiaryItem(state: DiaryState, index: Int, onEvent: (DiaryEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = state.diarys[index].title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = state.diarys[index].description,
                fontSize = 16.sp,
                 color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        IconButton(onClick = {
            onEvent(DiaryEvent.DeleteDiary(state.diarys[index]))
        }
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Hapus Diary",
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.onSecondaryContainer

            )
        }
    }
}