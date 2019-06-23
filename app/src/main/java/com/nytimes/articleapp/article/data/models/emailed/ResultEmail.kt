package com.nytimes.articleapp.article.data.models.emailed

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class ResultEmail (
    @PrimaryKey
    var id:Long?,
    var url: String?,
    var adx_keywords: String?,
    var subsection: String?,
    var email_count: Int?,
    var count_type:String?,
    var column:String?,
    var eta_id:Long?,
    var section:String?,
    var asset_id:Long,
    var nytdsection:String?,
    var byline:String?,
    var type:String?,
    var title:String?,
    @SerializedName("abstract")
    var _abstract:String? = null,
    var published_date:String?,
    var source:String?,
    var updated:String?,
    var media:List<MediumEmail> ? = null,
    var uri:String?,
    var saved:Boolean = false
):Parcelable