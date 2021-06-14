package pse.at.swivl.ui.movies.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Movie(
    val title: String,
    val year: String,
    val cast: ArrayList<String>,
    val genres: ArrayList<String>,
    val rating: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
) : Parcelable