package com.example.newsdemo.data.local.db

import androidx.room.TypeConverter
import com.example.newsdemo.data.remote.modelDto.topHeadlines.Source

/**
 * @author : Mingaleev D
 * @data : 8/12/2022
 */

class Converters {

   @TypeConverter
   fun fromSource(source: Source): String? {
         return source.name
   }

   @TypeConverter
   fun toSource(name:String):Source{
      return Source(name,name)
   }
}