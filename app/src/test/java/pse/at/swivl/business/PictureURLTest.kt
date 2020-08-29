package pse.at.swivl.business

import org.junit.Assert
import org.junit.Test
import pse.at.swivl.ui.movies.domain.models.MoviePicture

class PictureURLTest {
    @Test
    fun validPictureURL() {
        Assert.assertTrue(
            MoviePicture(
                "50270240221",
                "0692122587",
                "65535",
                66,
                ""
            ).getImageURL() == "https://farm66.static.flickr.com/65535/50270240221_0692122587.jpg"
        )
    }

    @Test
    fun invalidPictureURL() {
        Assert.assertFalse(
            MoviePicture(
                "50270240221",
                "0692122587",
                "65535",
                66,
                ""
            ).getImageURL() == "https://farm66.static.flickr.com/65535/50270240221_0692122587"
        )
    }

}