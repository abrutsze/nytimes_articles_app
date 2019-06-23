package com.nytimes.articleapp.article.data.repository.sharedRepository

import com.nytimes.articleapp.article.data.models.shared.ResultShare
import io.reactivex.Completable
import io.reactivex.Observable

interface SharedRepo {

    fun getAllShared(): Observable<List<ResultShare>>
    fun getAllShareSaved(): Observable<List<ResultShare>>
    fun saveItem(resultShare: ResultShare, state:Boolean): Completable

}