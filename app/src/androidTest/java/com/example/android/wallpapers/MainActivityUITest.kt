package com.example.android.wallpapers

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.wallpapers.wallpapers.DetailActivity
import com.xwray.groupie.ViewHolder
import org.junit.runner.RunWith
import android.support.test.InstrumentationRegistry.getTargetContext
import android.content.ComponentName
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.view.View
import android.view.ViewGroup
import com.example.android.wallpapers.categories.CategoriesFragment
import com.example.android.wallpapers.utilities.ListenerUtil
import com.example.android.wallpapers.wallpapers.WallpaperFragment
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.*

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityUITest{

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun before(){
        Intents.init()
    }

    @After
    fun after(){
        Intents.release()
    }


    @Test
    fun onWallpaperClick_isCorrect(){
        onView(withId(R.id.wallpaper_recyclerview))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
        intended(hasComponent(ComponentName(getTargetContext(), DetailActivity::class.java!!)))
    }


    @Test
    fun categorieNavClicked() {
        val bottomNavigationItemView = onView(
            Matchers.allOf(
                withId(R.id.categories), ViewMatchers.withContentDescription("Categories"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())
        val fragment= ListenerUtil.getSelectedFragment()
        Assert.assertEquals(CategoriesFragment()::class.java, fragment::class.java)
    }

    @Test
    fun favouritesNavClicked() {
        val bottomNavigationItemView = onView(
            Matchers.allOf(
                withId(R.id.favourites), ViewMatchers.withContentDescription("Favourites"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    2
                ),
                ViewMatchers.isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())
        val fragment= ListenerUtil.getSelectedFragment()
        Assert.assertEquals(FavouritesFragment()::class.java, fragment::class.java)
    }

    @Test
    fun settingsNavClicked() {
        val bottomNavigationItemView = onView(
            Matchers.allOf(
                withId(R.id.settings), ViewMatchers.withContentDescription("Settings"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    3
                ),
                ViewMatchers.isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())
        val fragment= ListenerUtil.getSelectedFragment()
        Assert.assertEquals(SettingsFragment()::class.java, fragment::class.java)
    }

    @Test
    fun wallpapersNavClicked() {
        val bottomNavigationItemView = onView(
            Matchers.allOf(
                withId(R.id.wallpapers), ViewMatchers.withContentDescription("Wallpapers"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())
        val fragment= ListenerUtil.getSelectedFragment()
        Assert.assertEquals(WallpaperFragment()::class.java, fragment::class.java)
    }


    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

}