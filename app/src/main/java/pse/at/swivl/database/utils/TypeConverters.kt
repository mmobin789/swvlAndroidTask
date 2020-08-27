package pse.at.swivl.database.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverters {
    private val mGson = Gson()
    private val listType = object : TypeToken<ArrayList<String>?>() {}.type

    /**
     * Converts a string list to a String for saving in or reading from Room.
     */
    @TypeConverter
    fun stringListToString(list: ArrayList<String>): String = mGson.toJson(list)

    /**
     * Converts a string to a String list for saving in or reading from Room.
     */
    @TypeConverter
    fun stringToStringList(s: String): ArrayList<String> = mGson.fromJson(s, listType)

}