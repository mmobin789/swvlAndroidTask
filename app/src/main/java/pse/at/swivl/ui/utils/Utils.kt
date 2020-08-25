package pse.at.swivl.ui.utils

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

object Utils {
    /**
     * This method can be used to read a json file.
     * For now it specifically reads movies.json file from assets folder.
     * @param context context of app.
     */
    fun loadJSONStringFromAsset(
        context: Context
    ): String? {
        try {
            val `is`: InputStream = context.assets.open("movies.json")
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            return JSONObject(String(buffer)).getJSONArray("movies").toString()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return null
    }
}