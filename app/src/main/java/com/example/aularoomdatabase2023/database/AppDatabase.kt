package com.example.aularoomdatabase2023.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aularoomdatabase2023.dao.ExpenseDao
import com.example.aularoomdatabase2023.dao.TravelDao
import com.example.aularoomdatabase2023.dao.UserDao
import com.example.aularoomdatabase2023.entity.Expense
import com.example.aularoomdatabase2023.entity.Travel
import com.example.aularoomdatabase2023.entity.User

@Database(entities = [User::class, Travel::class, Expense::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun travelDao(): TravelDao
    abstract fun expenseDao(): ExpenseDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(application: Application): AppDatabase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                "my-db7"
            ).build()
            INSTANCE = instance
            instance
        }
    }

}