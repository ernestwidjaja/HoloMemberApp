package com.example.jetsubmissionapp.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetsubmissionapp.data.remote.response.ChannelsResponseItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MembersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(membersEntity: ChannelsResponseItem)

    @Delete
    suspend fun delete(membersEntity: ChannelsResponseItem)

    @Query("SELECT * FROM members")
    fun getFavoriteUser(): Flow<List<ChannelsResponseItem>>

    @Query("SELECT EXISTS(SELECT * FROM members WHERE name = :name)")
    fun isFavorite(name: String): LiveData<Boolean>

}