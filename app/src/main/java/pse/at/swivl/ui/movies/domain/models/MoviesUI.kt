package pse.at.swivl.ui.movies.domain.models

sealed class MoviesUI {
    object Loading : MoviesUI()
    class Success(val movies: List<Movie>) : MoviesUI()
    //class Failed(val error: String) : MoviesUI()  // data always loads from pre-packed file into DB without any user interaction.
}