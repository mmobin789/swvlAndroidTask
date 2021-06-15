package pse.at.swivl.ui

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pse.at.swivl.ui.detail.DetailActivity
import pse.at.swivl.utils.TestUtils

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<DetailActivity>()

    @Test
    fun testMovieDetailUI() {
        val movieTitle = "Avengers"

        activityScenarioRule.scenario.run {
            onActivity {
                it.startActivity(
                    Intent(it, DetailActivity::class.java).putExtra(
                        "movie",
                        TestUtils.createMovie(1, movieTitle)
                    )
                )

            }
            Espresso.onView(ViewMatchers.withText(movieTitle))
                .check(ViewAssertions.matches(ViewMatchers.withText(movieTitle)))
        }
    }

}