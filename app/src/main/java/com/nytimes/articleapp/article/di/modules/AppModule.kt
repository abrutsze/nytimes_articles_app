package com.nytimes.articleapp.article.di.modules

import android.content.Context
import androidx.room.Room
import com.nytimes.articleapp.article.App
import com.nytimes.articleapp.article.data.database.AppDatabase
import com.nytimes.articleapp.article.data.database.EmailedDao
import com.nytimes.articleapp.article.data.database.SharedDao
import com.nytimes.articleapp.article.data.database.ViewedDao
import com.nytimes.articleapp.article.data.network.Api
import com.nytimes.articleapp.article.data.repository.emailedRepository.EmailedRepo
import com.nytimes.articleapp.article.data.repository.emailedRepository.EmailedRepository
import com.nytimes.articleapp.article.data.repository.sharedRepository.SharedRepo
import com.nytimes.articleapp.article.data.repository.sharedRepository.SharedRepository
import com.nytimes.articleapp.article.data.repository.viewedRepository.ViewedRepo
import com.nytimes.articleapp.article.data.repository.viewedRepository.ViewedRepository
import com.nytimes.articleapp.article.utils.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    fun providesApp(): App = app

    @Provides
    @Singleton
    internal fun provideContext(): Context =app.applicationContext


    @Singleton
    @Provides
    internal fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DB_NAME).build()
    }


    @Singleton
    @Provides
    internal fun providesResultViewAppDataBaseDao(database: AppDatabase): ViewedDao {
        return database.viewedDao
    }


    @Singleton
    @Provides
    internal fun provideViewedRepository(context: Context, dataBase: AppDatabase, api: Api): ViewedRepository {
        return ViewedRepository(context, dataBase, api)
    }

    @Singleton
    @Provides
    internal fun provideViewedRepo(viewedRepository: ViewedRepository): ViewedRepo {
        return viewedRepository
    }


    @Singleton
    @Provides
    internal fun providesEmailedAppDataBaseDao(database: AppDatabase): EmailedDao {
        return database.emailedDao
    }


    @Singleton
    @Provides
    internal fun provideEmailedRepository(context: Context, dataBase: AppDatabase, api: Api): EmailedRepository {
        return EmailedRepository(context, dataBase, api)
    }

    @Singleton
    @Provides
    internal fun provideEmailedRepo(emailedRepository: EmailedRepository): EmailedRepo {
        return emailedRepository
    }


    @Singleton
    @Provides
    internal fun providesSharedAppDataBaseDao(database: AppDatabase): SharedDao {
        return database.sharedDao
    }


    @Singleton
    @Provides
    internal fun provideSharedRepository(context: Context, dataBase: AppDatabase, api: Api): SharedRepository {
        return SharedRepository(context, dataBase, api)
    }

    @Singleton
    @Provides
    internal fun provideSharedRepo(sharedRepository: SharedRepository): SharedRepo {
        return sharedRepository
    }
}