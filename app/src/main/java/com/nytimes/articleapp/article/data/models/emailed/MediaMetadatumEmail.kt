package com.nytimes.articleapp.article.data.models.emailed

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MediaMetadatumEmail (
    val url: String ,
    val format:String ,
    val height:Int,
    val width:Int
):Parcelable