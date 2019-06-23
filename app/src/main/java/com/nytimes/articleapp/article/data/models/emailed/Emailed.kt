package com.nytimes.articleapp.article.data.models.emailed

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Emailed (
    val  status:String,
    val copyright:String ,
    val numResults:Integer ,
    val results: List<ResultEmail> ? = null
):Parcelable