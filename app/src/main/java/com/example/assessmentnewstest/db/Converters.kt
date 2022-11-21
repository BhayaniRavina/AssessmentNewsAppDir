package com.example.assessmentnewstest.db

import androidx.room.TypeConverter
import com.example.assessmentnewstest.models.Images5
import com.example.assessmentnewstest.models.TypeAttributes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromSource(source: TypeAttributes): String {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toImageSource(name: String): TypeAttributes {
        val listType = object : TypeToken<TypeAttributes>() {
        }.type
        return Gson().fromJson(name,listType)
    }

    @TypeConverter
    fun fromImageSource(source: Images5): String {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(name: String): Images5 {
        val listType = object : TypeToken<Images5>() {
        }.type
        return Gson().fromJson(name,listType)
    }
}