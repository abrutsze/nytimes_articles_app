package com.nytimes.articleapp.article.data.repository.viewedRepository

import com.nytimes.articleapp.article.data.models.viewed.ResultView
import io.reactivex.Completable
import io.reactivex.Observable

interface ViewedRepo {
    fun getAllViewed(): Observable<List<ResultView>>
    fun getAllViewSaved(): Observable<List<ResultView>>
    fun saveItem(resultView: ResultView ,state:Boolean): Completable
}