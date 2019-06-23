package com.nytimes.articleapp.article

import android.app.Application
import com.nytimes.articleapp.article.di.component.DIComponent
import com.nytimes.articleapp.article.di.component.DaggerDIComponent
import com.nytimes.articleapp.article.di.modules.ApiModule
import com.nytimes.articleapp.article.di.modules.AppModule

class App : Application() {

    lateinit var di : DIComponent

    override fun onCreate() {
        super.onCreate()

        di = DaggerDIComponent
            .builder()
            .apiModule(ApiModule())
            .appModule(AppModule(this))
            .build()

    }
}