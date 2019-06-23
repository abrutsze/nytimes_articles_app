package com.nytimes.articleapp.article.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nytimes.articleapp.article.data.models.shared.ResultShare
import com.nytimes.articleapp.article.data.repository.sharedRepository.SharedRepo
import com.nytimes.articleapp.article.di.component.DIComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SharedViewModel : ViewModel(),DIComponent.Injectable {

    var sharedResult: MutableLiveData<List<ResultShare>> = MutableLiveData()
    var sharedError: MutableLiveData<String> = MutableLiveData()
    var sharedLoader: MutableLiveData<Boolean> = MutableLiveData()

    @Inject
    lateinit var sharedRepo: SharedRepo
    override fun inject(diComponent: DIComponent) {
        diComponent.inject(this)
    }


    fun getAllShared() {
        val observer = object : DisposableObserver<List<ResultShare>>() {
            override fun onNext(sharedResult1: List<ResultShare>) {
                sharedResult.postValue(sharedResult1)
                sharedLoader.postValue(false)
            }


            override fun onError(e: Throwable) {
                sharedError.postValue(e.message)
                sharedLoader.postValue(false)
            }

            override fun onComplete() {

            }
        }


        sharedRepo!!.getAllShared().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(observer)
    }

    fun getAllShareSaved() {
        val observer = object : DisposableObserver<List<ResultShare>>() {
            override fun onNext(sharedResult1: List<ResultShare>) {
                sharedResult.postValue(sharedResult1)
                sharedLoader.postValue(false)
            }


            override fun onError(e: Throwable) {
                sharedError.postValue(e.message)
                sharedLoader.postValue(false)
            }

            override fun onComplete() {

            }
        }


        sharedRepo!!.getAllShareSaved().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(observer)
    }

    fun saveItem(resultShare: ResultShare , state:Boolean){
        sharedRepo!!.saveItem(resultShare,state).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}