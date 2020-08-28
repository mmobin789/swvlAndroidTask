package pse.at.swivl.utils

import pse.at.swivl.ui.main.domain.models.Movie

object TestUtils {

    fun createMovie(id: Int, title: String) = Movie(
        title, "2012", arrayListOf("RDJ", "Scarlett Johannson"),
        arrayListOf("Sci fi", "Action"), 5, id
    )

    fun createMovies(movieCount: Int): List<Movie> {
        val list = ArrayList<Movie>(movieCount)
        for (i in 1..movieCount)
            list.add(
                Movie(
                    "Movie $i", "201$i", arrayListOf("RDJ", "Scarlett Johannson"),
                    arrayListOf("Sci fi", "Action"), 5, i
                )
            )

        return list
    }

}