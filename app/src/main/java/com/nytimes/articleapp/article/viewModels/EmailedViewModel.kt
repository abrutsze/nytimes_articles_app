package com.nytimes.articleapp.article.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nytimes.articleapp.article.data.models.emailed.ResultEmail
import com.nytimes.articleapp.article.data.repository.emailedRepository.EmailedRepo
import com.nytimes.articleapp.article.di.component.DIComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class EmailedViewModel : ViewModel(),DIComponent.Injectable {
    var emailedResult: MutableLiveData<List<ResultEmail>> = MutableLiveData()
    var emailedError: MutableLiveData<String> = MutableLiveData()
    var emailedLoader: MutableLiveData<Boolean> = MutableLiveData()

    @Inject
    lateinit var emailedRepo: EmailedRepo
    override fun inject(diComponent: DIComponent) {
        diComponent.inject(this)
    }


    fun getAllEmailed() {
        val observer = object : DisposableObserver<List<ResultEmail>>() {
            override fun onNext(emailedResult1: List<ResultEmail>) {
                emailedResult.postValue(emailedResult1)
                emailedLoader.postValue(false)
            }


            override fun onError(e: Throwable) {
                emailedError.postValue(e.message)
                emailedLoader.postValue(false)
            }

            override fun onComplete() {

            }
        }


        emailedRepo!!.getAllEmailed().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(observer)
    }

    fun getAllEmailSaved() {
        val observer = object : DisposableObserver<List<ResultEmail>>() {
            override fun onNext(emailedResult1: List<ResultEmail>) {
                emailedResult.postValue(emailedResult1)
                emailedLoader.postValue(false)
            }


            override fun onError(e: Throwable) {
                emailedError.postValue(e.message)
                emailedLoader.postValue(false)
            }

            override fun onComplete() {

            }
        }


        emailedRepo!!.getAllEmailSaved().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(observer)
    }

    fun saveItem(resultEmail: ResultEmail , state:Boolean){
        emailedRepo!!.saveItem(resultEmail,state).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}