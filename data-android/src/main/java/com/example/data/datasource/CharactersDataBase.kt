package com.example.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.datasource.local.CharacterDao
import com.example.data.datasource.model.MyResponseEntity

@Database(entities = [MyResponseEntity::class], version = 1, exportSchema = false)
@TypeConverters(com.example.data.datasource.local.TypeConverters::class)
abstract class CharactersDataBase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}