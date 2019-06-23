package com.nytimes.articleapp.article.data.models.shared

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class ResultShare (
    @PrimaryKey
    var id : Long =0,
    var url : String? = null,
    var adx_adx_keywordswords : String? = null,
    var subsection : String? = null,
    var share_count : Int?=null,
    var count_type : String?=null,
    var column : String?=null,
    var eta_id : Long?=null,
    var section : String?=null,
    var asset_id : Long?=null,
    var nytdsection : String?=null,
    var byline : String?=null,
    var type : String?=null,
    var title : String?=null,
    @SerializedName("abstract")
    var abstract : String?=null,
    var published_date : String?=null,
    var source : String?=null,
    var updated : String?=null,
    var media : List<MediaShare>? = null,
    var uri : String?=null,
    var saved:Boolean?= false
): Parcelable