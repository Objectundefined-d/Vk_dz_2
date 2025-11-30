package com.example.vk_dz_2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vk_dz_2.data.local.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Query("SELECT * FROM images")
    fun getAllImages(): List<ImageEntity>

    @Query("SELECT * FROM images")
    fun getAllImagesFlow(): Flow<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<ImageEntity>)

    @Query("DELETE FROM images")
    suspend fun clearAll()

    suspend fun clearAndInsertAll(images: List<ImageEntity>) {
        clearAll()
        insertAll(images)
    }
}