package pse.at.swivl.database.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverters {
    private val mGson = Gson()
    private val listType = object : TypeToken<ArrayList<String>?>() {}.type

    @TypeConverter
    fun stringListToString(list: ArrayList<String>) = mGson.toJson(list)

    @TypeConverter
    fun stringToStringList(s: String) = mGson.fromJson<ArrayList<String>>(s, listType)

}