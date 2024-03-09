package kr.loner.willog.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kr.loner.willog.data.local.dao.BookmarkedPhotoDao
import kr.loner.willog.data.local.entity.BookmarkedPhotoEntity

@Database(entities = [BookmarkedPhotoEntity::class], version = 1)
internal abstract class WillogDataBase : RoomDatabase() {
    abstract fun bookmarkedPhotoDao(): BookmarkedPhotoDao
}