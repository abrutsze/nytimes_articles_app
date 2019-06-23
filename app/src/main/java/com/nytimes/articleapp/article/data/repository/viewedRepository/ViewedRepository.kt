package com.nytimes.articleapp.article.data.repository.viewedRepository

import android.content.Context
import com.nytimes.articleapp.article.data.database.AppDatabase
import com.nytimes.articleapp.article.data.models.viewed.ResultView
import com.nytimes.articleapp.article.data.network.Api
import com.nytimes.articleapp.article.utils.AppConstants
import com.nytimes.articleapp.article.utils.isNetworkConnected
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ViewedRepository@Inject constructor(private val context: Context, private val dataBase: AppDatabase, private val api: Api) : ViewedRepo {
    override fun saveItem(resultView: ResultView, state: Boolean): Completable =
        Completable.create { dataBase.viewedDao.updateById(resultView.id!!,state) }

    override fun getAllViewSaved(): Observable<List<ResultView>> {
        return dataBase.viewedDao.getAllResultViewSaved(true).toObservable()
    }

    override fun getAllViewed(): Observable<List<ResultView>> {
        val hasConnection = isNetworkConnected(context)
        var observableFromApi : Observable<List<ResultView>>? = null


        if (hasConnection){
            observableFromApi = getViewedFromApi()
        }
        var observableFromDb = getViewedFromDb()


        return if (hasConnection) Observable.concat(observableFromDb ,observableFromApi)
        else observableFromDb
    }


    private fun getViewedFromApi():Observable<List<ResultView>>?{
        return  api.getAllViewed(AppConstants.API_KEY).doOnNext{
            for (item:ResultView in it.results!!) {
                item?.apply {
                    dataBase.viewedDao.insert(item)
                }
            }
        }.map{ it?.results }
    }

    private fun getViewedFromDb():Observable<List<ResultView>> {
        return dataBase.viewedDao.getAllResultView(true).toObservable()
    }

}