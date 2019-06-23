package com.nytimes.articleapp.article.data.models.shared

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shared (

    val status : String,
    val copyright : String,
    val num_results : Int,
    val results : List<ResultShare> ? =null
): Parcelable