package kr.loner.willog.data.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.loner.willog.data.local.WillogDataBase
import kr.loner.willog.data.local.dao.BookmarkedPhotoDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataBaseModule {
    @Singleton
    @Provides
    fun provideWillogDataBase(@ApplicationContext context: Context): WillogDataBase =
        Room
        .databaseBuilder(context, WillogDataBase::class.java, "willog_dataBase")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideBookmarkedPhotoDao(dataBase: WillogDataBase): BookmarkedPhotoDao =
        dataBase.bookmarkedPhotoDao()


}