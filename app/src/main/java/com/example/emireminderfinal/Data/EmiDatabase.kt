package com.example.emireminderfinal.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Emi::class], version = 1, exportSchema = false)
abstract class EmiDatabase : RoomDatabase() {

    abstract fun emiDao(): EmiDao

    companion object {
        @Volatile
        private var INSTANCE: EmiDatabase? = null

        fun getDatabase(context: Context):EmiDatabase{
            val tempInstance= INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    EmiDatabase::class.java,
                    "emi_database"
                ).build()
                INSTANCE=instance
                return  instance
            }
        }
    }

}