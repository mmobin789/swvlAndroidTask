package pse.at.swivl.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pse.at.swivl.R
import pse.at.swivl.ui.movies.MoviesFragment
import pse.at.swivl.ui.movies.adapter.MoviesAdapter

@RunWith(AndroidJUnit4::class)
class MoviesFragmentTest {
    @Before
    fun launchMoviesFragment() {
        launchFragmentInContainer<MoviesFragment>()
    }

    @Test
    fun testMoviesUILoadingSuccess() {
        onView(withId(R.id.pBar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv))
            .perform(RecyclerViewActions.scrollToPosition<MoviesAdapter.VH>(10))
    }

    @Test
    fun testMoviesUIOnListClick() {

        onView(withId(R.id.pBar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv))
            .perform(RecyclerViewActions.scrollToPosition<MoviesAdapter.VH>(10))
            .perform(ViewActions.click())
    }

    @Test
    fun testMoviesUISearch() {
        onView(withId(R.id.sv)).perform(ViewActions.typeText("io"))
            .perform()
    }

    @Test
    fun testMoviesUIDetailOpening() {
        onView(withId(R.id.rv)).perform(RecyclerViewActions.scrollToPosition<MoviesAdapter.VH>(0))
            .perform(ViewActions.click())
    }

    @Test
    fun testMoviesUIDetailOpeningWithSearch() {
        onView(withId(R.id.sv)).perform(ViewActions.typeText("io"))
        onView(withId(R.id.rv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesAdapter.VH>(
                0,
                ViewActions.click()
            )
        )
    }
}