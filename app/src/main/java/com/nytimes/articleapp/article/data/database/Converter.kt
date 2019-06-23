package com.nytimes.articleapp.article.data.database

import androidx.room.TypeConverter
import com.nytimes.articleapp.article.data.models.emailed.MediumEmail
import com.nytimes.articleapp.article.data.models.shared.MediaShare
import com.nytimes.articleapp.article.data.models.viewed.MediaView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    var gson: Gson = Gson()

    @TypeConverter
    fun fromStringToListMediaView( data: String?) : List<MediaView>? {
        if( data == null ) {
            return null
        }

        val obj = object : TypeToken<List<MediaView>>(){}.type

        return gson.fromJson(data, obj)
    }

    @TypeConverter
    fun fromListMediaViewToString( someObjects : List<MediaView>?) : String? {
        if(someObjects == null) {
            return null
        }
        return gson.toJson(someObjects)
    }



    @TypeConverter
    fun fromStringToListMediaShare( data: String?) : List<MediaShare>? {
        if( data == null ) {
            return null
        }

        val obj = object : TypeToken<List<MediaShare>>(){}.type

        return gson.fromJson(data, obj)
    }

    @TypeConverter
    fun fromListMediaShareToString( someObjects : List<MediaShare>?) : String? {
        if(someObjects == null) {
            return null
        }
        return gson.toJson(someObjects)
    }


    @TypeConverter
    fun fromStringToListMediumEmail( data: String?) : List<MediumEmail>? {
        if( data == null ) {
            return null
        }

        val obj = object : TypeToken<List<MediumEmail>>(){}.type

        return gson.fromJson(data, obj)
    }

    @TypeConverter
    fun fromListMediumEmailToString( someObjects : List<MediumEmail>?) : String? {
        if(someObjects == null) {
            return null
        }
        return gson.toJson(someObjects)
    }



    @TypeConverter
    fun fromStringToListStringEmail( data: String?) : List<String>? {
        if( data == null ) {
            return null
        }

        val obj = object : TypeToken<List<String>>(){}.type

        return gson.fromJson(data, obj)
    }

    @TypeConverter
    fun fromListStringEmailToString( someObjects : List<String>?) : String? {
        if(someObjects == null) {
            return null
        }
        return gson.toJson(someObjects)
    }


}