package pse.at.swivl.ui.movies.repository

import pse.at.swivl.ui.movies.domain.dao.MovieDao
import pse.at.swivl.ui.movies.domain.models.Movie

class LocalSource(private val movieDao: MovieDao) {


    fun addMovies(movies:List<Movie>) = movieDao.addMovies(movies)
    /**
     * Each search query will trigger IO bound threads to search on database.
     * This will scramble the UX so this method which runs the query is called only in a synchronized block so only single thread access is allowed.
     */
    fun findMoviesByTitle(title: String, maxResults: Int, rating: Int) =
        movieDao.findMoviesByTitle("%$title%", maxResults, rating)

    /**
     * The movies are cached on 1st app launch from movies.json file and then loaded in sorted order from database.
     */
    fun getMovies() = movieDao.getMovies()

}