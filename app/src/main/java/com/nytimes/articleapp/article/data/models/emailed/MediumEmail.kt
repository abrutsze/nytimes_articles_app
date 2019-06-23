package com.nytimes.articleapp.article.data.models.emailed

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MediumEmail (
    val type:String ,
    val subtype:String ,
    val caption:String ,
    val copyright:String ,
    val approved_for_syndication:Int,
    @SerializedName("media-metadata")
    val mediaMetadata:List<MediaMetadatumEmail>?  = null
):Parcelable