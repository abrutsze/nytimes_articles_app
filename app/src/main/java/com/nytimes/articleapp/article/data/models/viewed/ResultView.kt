package com.nytimes.articleapp.article.data.models.viewed

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class ResultView (
    @PrimaryKey
    var id : Long =0,
    var url : String ?=null,
    var adx_adx_keywordswords : String?=null,
    var column : String?=null,
    var section : String?=null,
    var byline : String?=null,
    var type : String?=null,
    var title : String?=null,
    @SerializedName("abstract")
    var abstract : String?=null,
    var published_date : String?=null,
    var source : String?=null,
    var asset_id : Long?=null,
    var views : Int?=null,
    var media : List<MediaView>? = null,
    var uri : String?=null,
    var saved:Boolean? = false

): Parcelable