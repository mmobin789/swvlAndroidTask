package pse.at.swivl.ui.movies.domain.models

sealed class PhotosAPIResponse {
    class Success(val moviePictures: List<MoviePicture>) : PhotosAPIResponse()
    class Failed(val error: String) : PhotosAPIResponse()
}