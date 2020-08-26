package pse.at.swivl.ui.main.domain.models

import com.google.gson.annotations.SerializedName

class Photos(val photos: Data) {
    class Data(@SerializedName("photo") val pictures: List<MoviePicture>)
}