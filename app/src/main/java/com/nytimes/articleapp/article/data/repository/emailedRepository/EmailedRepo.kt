package com.nytimes.articleapp.article.data.repository.emailedRepository

import com.nytimes.articleapp.article.data.models.emailed.ResultEmail
import io.reactivex.Completable
import io.reactivex.Observable

interface EmailedRepo {
    fun getAllEmailed(): Observable<List<ResultEmail>>
    fun getAllEmailSaved(): Observable<List<ResultEmail>>
    fun saveItem(resultEmail: ResultEmail , state:Boolean):Completable
}