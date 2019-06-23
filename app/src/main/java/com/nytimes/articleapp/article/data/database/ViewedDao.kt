package com.nytimes.articleapp.article.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nytimes.articleapp.article.data.models.viewed.ResultView
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ViewedDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(resultView: ResultView)

    @Query("Delete from ResultView")
    fun deleteAll()

    @Query("Delete from ResultView WHERE id = :id ")
    fun deleteById(id: Long): Completable

    @Query("Update ResultView set saved = :saved Where id = :id")
    fun updateById(id: Long , saved: Boolean)

    @Query("SELECT * FROM ResultView WHERE id = :id")
    fun resultViewById(id: Long): Single<ResultView>

    @Query("SELECT * from ResultView ORDER BY saved = :state desc")
    fun getAllResultView(state: Boolean): Single<List<ResultView>>

    @Query("SELECT * from ResultView Where saved = :saved ORDER BY published_date ASC")
    fun getAllResultViewSaved(saved:Boolean): Single<List<ResultView>>
}