package com.nytimes.articleapp.article.data.repository.sharedRepository

import android.content.Context
import com.nytimes.articleapp.article.data.database.AppDatabase
import com.nytimes.articleapp.article.data.models.shared.ResultShare
import com.nytimes.articleapp.article.data.network.Api
import com.nytimes.articleapp.article.utils.AppConstants
import com.nytimes.articleapp.article.utils.iomain
import com.nytimes.articleapp.article.utils.isNetworkConnected
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class SharedRepository @Inject constructor(private val context: Context, private val dataBase: AppDatabase, private val api: Api): SharedRepo {
    override fun saveItem(resultShare: ResultShare, state: Boolean): Completable  =
        Completable.create { dataBase.sharedDao.updateById(resultShare.id!!,state) }

    override fun getAllShareSaved(): Observable<List<ResultShare>> {
        return dataBase.sharedDao.getAllResultShareSaved(true).iomain().toObservable()
    }

    override fun getAllShared(): Observable<List<ResultShare>> {
        val hasConnection = isNetworkConnected(context)
        var observableFromApi : Observable<List<ResultShare>>? = null


        if (hasConnection){
            observableFromApi = getSharedFromApi()
        }
        var observableFromDb = getSharedFromDb()


        return if (hasConnection) Observable.concat(observableFromDb ,observableFromApi)
        else observableFromDb
    }


    private fun getSharedFromApi():Observable<List<ResultShare>>?{
        return  api.getAllShared(AppConstants.API_KEY).doOnNext{
            for (item:ResultShare in it.results!!) {
                item?.apply {
                    dataBase.sharedDao.insert(item)
                }
            }
        }.map{ it?.results }
    }

    private fun getSharedFromDb():Observable<List<ResultShare>> {
        return dataBase.sharedDao.getAllResultShare(true).toObservable()
    }
}