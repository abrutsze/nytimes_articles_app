package com.nytimes.articleapp.article.data.models.shared

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MediaMetadatumShare (

    val url : String,
    val format : String,
    val height : Int,
    val width : Int
):Parcelable