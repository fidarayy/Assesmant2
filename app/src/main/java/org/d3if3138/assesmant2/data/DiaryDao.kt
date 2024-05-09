package org.d3if3138.assesmant2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {

    @Upsert
    suspend fun  upsertDiary(diary: Diary)

    @Delete
    suspend fun deleteDiary(diary: Diary)

    @Query("SELECT * FROM diary ORDER BY dateAdded")
    fun getDiarysOrderByDateAdded(): Flow<List<Diary>>

    @Query("SELECT * FROM diary ORDER BY title ASC")
    fun getDiarysOrderByTitle(): Flow<List<Diary>>
}
