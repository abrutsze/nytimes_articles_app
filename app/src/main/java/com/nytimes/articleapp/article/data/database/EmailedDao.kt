package com.nytimes.articleapp.article.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nytimes.articleapp.article.data.models.emailed.ResultEmail
import io.reactivex.Single

@Dao
interface EmailedDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(resultEmail: ResultEmail)

    @Query("Delete from ResultEmail")
    fun deleteAll()

    @Query("Delete from ResultEmail WHERE id = :id ")
    fun deleteById(id: Long)

    @Query("Update ResultEmail set saved = :saved Where id = :id")
    fun updateById(id: Long , saved: Boolean)

    @Query("SELECT * FROM ResultEmail WHERE id = :id")
    fun resultEmailById(id: Long): Single<ResultEmail>

    @Query("SELECT * from ResultEmail ORDER BY saved = :state desc")
    fun getAllResultEmail(state:Boolean): Single<List<ResultEmail>>

    @Query("SELECT * from ResultEmail Where saved = :saved ORDER BY published_date ASC")
    fun getAllResultEmailSaved(saved:Boolean): Single<List<ResultEmail>>
}