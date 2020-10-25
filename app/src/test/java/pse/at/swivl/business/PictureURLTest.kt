package pse.at.swivl.business

import org.junit.Assert
import org.junit.Test
import pse.at.swivl.ui.movies.domain.models.MoviePicture

class PictureURLTest {


    private fun isPal(str: String, s: Int = 0, e: Int = str.lastIndex): Boolean {

        return when {
            e - s <= 1 -> true
            str[s] == str[e] -> isPal(str, s + 1, e - 1)
            else -> false
        }

    }

    @Test
    fun palTest() {
        val result = isPal("anna")
        Assert.assertTrue(result)
    }

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