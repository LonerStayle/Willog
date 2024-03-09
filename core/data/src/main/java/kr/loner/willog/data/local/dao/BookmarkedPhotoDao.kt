package kr.loner.willog.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kr.loner.willog.data.local.entity.BookmarkedPhotoEntity

@Dao
internal interface BookmarkedPhotoDao {
    @Query("SELECT * from BookmarkedPhotoEntity")
    fun getBookmarkedPhotos(): Flow<List<BookmarkedPhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bookmarkedPhotoEntity: BookmarkedPhotoEntity)

    @Query("DELETE FROM BookmarkedPhotoEntity WHERE id = :id")
    suspend fun deleteById(id: String)
}