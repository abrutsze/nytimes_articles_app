package com.nytimes.articleapp.article.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nytimes.articleapp.article.data.models.shared.ResultShare
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SharedDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(resultShare: ResultShare)

    @Query("Delete from ResultShare")
    fun deleteAll()

    @Query("Delete from ResultShare WHERE id = :id ")
    fun deleteById(id: Long): Completable

    @Query("Update ResultShare set saved = :saved Where id = :id")
    fun updateById(id: Long , saved: Boolean)

    @Query("SELECT * FROM ResultShare WHERE id = :id")
    fun resultsShareById(id: Long): Single<ResultShare>

    @Query("SELECT * from ResultShare ORDER BY saved = :state desc")
    fun getAllResultShare(state: Boolean): Single<List<ResultShare>>

    @Query("SELECT * from ResultShare Where saved = :saved ORDER BY published_date ASC")
    fun getAllResultShareSaved(saved:Boolean): Single<List<ResultShare>>
}