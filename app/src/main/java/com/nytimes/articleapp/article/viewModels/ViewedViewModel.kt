package com.nytimes.articleapp.article.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nytimes.articleapp.article.data.models.viewed.ResultView
import com.nytimes.articleapp.article.data.repository.viewedRepository.ViewedRepo
import com.nytimes.articleapp.article.di.component.DIComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ViewedViewModel : ViewModel(),DIComponent.Injectable {
    var viewedResult: MutableLiveData<List<ResultView>> = MutableLiveData()
    var viewedError: MutableLiveData<String> = MutableLiveData()
    var viewedLoader: MutableLiveData<Boolean> = MutableLiveData()


    @Inject
    lateinit var viewedRepo: ViewedRepo

    override fun inject(diComponent: DIComponent) {
        diComponent.inject(this)
    }


    fun getAllViewed() {
        val observer = object : DisposableObserver<List<ResultView>>() {
            override fun onNext(viewedResult1: List<ResultView>) {
                viewedResult.postValue(viewedResult1)
                viewedLoader.postValue(false)
            }


            override fun onError(e: Throwable) {
                viewedError.postValue(e.message)
                viewedLoader.postValue(false)
            }

            override fun onComplete() {

            }
        }


        viewedRepo!!.getAllViewed().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(observer)
    }


    fun getAllViewSaved() {
        val observer = object : DisposableObserver<List<ResultView>>() {
            override fun onNext(viewedResult1: List<ResultView>) {
                viewedResult.postValue(viewedResult1)
                viewedLoader.postValue(false)
            }


            override fun onError(e: Throwable) {
                viewedError.postValue(e.message)
                viewedLoader.postValue(false)
            }

            override fun onComplete() {

            }
        }


        viewedRepo!!.getAllViewSaved().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(observer)
    }

    fun saveItem(resultView: ResultView ,state:Boolean){
        viewedRepo!!.saveItem(resultView,state).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}