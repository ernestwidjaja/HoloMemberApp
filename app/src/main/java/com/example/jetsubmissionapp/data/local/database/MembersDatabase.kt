package com.example.jetsubmissionapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetsubmissionapp.data.remote.response.ChannelsResponseItem

@Database(
    entities = [ChannelsResponseItem::class],
    version = 3
)
abstract class MembersDatabase : RoomDatabase() {
    abstract fun membersDao(): MembersDao

    companion object {
        @Volatile
        private var instance: MembersDatabase? = null
        fun getInstance(context: Context): MembersDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    MembersDatabase::class.java, "Members.db"
                ).fallbackToDestructiveMigration().build()
            }
    }
}