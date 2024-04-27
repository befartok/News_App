package com.example.aston_intensiv_final

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Item::class], version = 2)
abstract class MainDB: RoomDatabase() {

    abstract fun getDao(): Dao
    companion object{
        fun getDB(context: Context): MainDB{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDB::class.java,
                "savedNews.db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}