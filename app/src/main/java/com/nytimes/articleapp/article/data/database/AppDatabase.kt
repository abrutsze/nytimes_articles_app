package com.nytimes.articleapp.article.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nytimes.articleapp.article.data.models.emailed.ResultEmail
import com.nytimes.articleapp.article.data.models.shared.ResultShare
import com.nytimes.articleapp.article.data.models.viewed.ResultView


@Database(entities = [ResultShare::class,ResultEmail ::class , ResultView::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val emailedDao : EmailedDao
    abstract val sharedDao: SharedDao
    abstract val viewedDao: ViewedDao
}