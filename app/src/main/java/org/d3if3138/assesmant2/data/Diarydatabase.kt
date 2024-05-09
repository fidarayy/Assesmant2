package org.d3if3138.assesmant2.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Diary::class], version = 1)
abstract class Diarydatabase: RoomDatabase() {
    abstract val dao: DiaryDao
}