package pse.at.swivl.ui.movies.repository

import pse.at.swivl.ui.movies.domain.dao.MovieDao
import pse.at.swivl.ui.movies.domain.models.Movie

class LocalSource(private val movieDao: MovieDao) {

    fun addMovies(movies: List<Movie>) = movieDao.addMovies(movies)

    fun findMoviesByTitle(title: String, maxResults: Int, rating: Int) =
        movieDao.findMoviesByTitle("%$title%", maxResults, rating)

    fun getMovies() = movieDao.getMovies()

}