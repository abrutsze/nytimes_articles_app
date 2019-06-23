package com.nytimes.articleapp.article.di.component

import androidx.annotation.Keep
import com.nytimes.articleapp.article.di.modules.ApiModule
import com.nytimes.articleapp.article.di.modules.AppModule
import com.nytimes.articleapp.article.view.activities.MainActivity
import com.nytimes.articleapp.article.viewModels.EmailedViewModel
import com.nytimes.articleapp.article.viewModels.SharedViewModel
import com.nytimes.articleapp.article.viewModels.ViewedViewModel
import dagger.Component
import javax.inject.Singleton

@Keep
@Singleton
@Component(modules = [AppModule::class , ApiModule::class])
interface DIComponent {

    interface Injectable{
        fun inject(diComponent: DIComponent)
    }

    fun inject(mainActivity: MainActivity)
    fun inject(emailedViewModel: EmailedViewModel)
    fun inject(viewedViewModel: ViewedViewModel)
    fun inject(sharedViewModel: SharedViewModel)
}