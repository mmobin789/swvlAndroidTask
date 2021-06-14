package pse.at.swivl.ui.movies.domain.models

sealed class MoviePicturesUI {
    object Loading : MoviePicturesUI()
    class Success(val movies: List<MoviePicture>) : MoviePicturesUI()
    class Failed(val error: String) : MoviePicturesUI()  // data always loads from pre-packed file into DB without any user interaction.
}