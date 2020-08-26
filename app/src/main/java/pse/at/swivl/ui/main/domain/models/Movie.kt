package pse.at.swivl.ui.main.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie(
    val title: String,
    val year: String,
    val cast: ArrayList<String>,
    val genres: ArrayList<String>,
    val rating: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
)