package com.nytimes.articleapp.article.data.models.viewed

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class MediaMetadataView (
    val url : String,
    val format : String,
    val height : Int,
    val width : Int
):Parcelable