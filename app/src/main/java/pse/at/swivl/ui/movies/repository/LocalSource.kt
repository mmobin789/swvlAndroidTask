package pse.at.swivl.ui.movies.repository

import pse.at.swivl.ui.movies.domain.dao.MovieDao
import pse.at.swivl.ui.movies.domain.models.Movie

class LocalSource(private val movieDao: MovieDao) {

    fun addMovies(movies: List<Movie>) = movieDao.addMovies(movies)


    fun getMovies() = movieDao.getMovies()


    //mohamed.nageh@swvl.com
    /**
     * This method will find the movies in order of year from database then group them into a map by year
     * and return at most 5 movies categorized by year.
     * (Thanks to Nageh for groupBy method suggestion)
     */
    fun findMoviesByTitle(title: String): List<Movie> {
        val movies = movieDao.findMoviesByTitle("%$title%")
        val map = movies.groupBy {
            it.year
        }
        val moviesByYear = ArrayList<Movie>(map.values.size)
        map.forEach {
            moviesByYear.addAll(it.value)
        }
        return if (moviesByYear.size > 5)
            moviesByYear.subList(0, 5)
        else moviesByYear
    }

}