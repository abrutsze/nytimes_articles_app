package com.nytimes.articleapp.article.data.models.viewed

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Viewed (
    val status : String,
    val copyright : String,
    val num_results : Int,
    val results : List<ResultView> ?= null
):Parcelable