package com.nytimes.articleapp.article.data.models.viewed

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MediaView (
    val type : String,
    val subtype : String,
    val caption : String,
    val copyright : String,
    val approved_for_syndication : Int,
    @SerializedName("media-metadata")
    val mediaMetadata : List<MediaMetadataView>
): Parcelable