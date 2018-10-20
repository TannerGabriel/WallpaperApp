package com.example.android.wallpapers

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.wallpapers.R.id.detail_picture_imageview
import com.example.android.wallpapers.wallpapers.DetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.action.ViewActions
import android.view.View
import android.view.ViewGroup
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.utilities.Constants
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher


@RunWith(AndroidJUnit4::class)
class DetailActivityUITest {



    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<DetailActivity> =
        object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, DetailActivity::class.java)
                val wallpaper = Wallpaper("veeterzy","https://images.pexels.com/photos/355423/pexels-photo-355423.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")
                result.putExtra(Constants.WALLPAPER, wallpaper)
                return result
            }
        }

    @Test
    fun imageViewIsVisible(){
        onView(withId(R.id.detail_picture_imageview)).check(matches(isDisplayed()))
    }

    @Test
    fun creatorTextViewIsVisible(){
        onView(withId(R.id.creator_textview)).check(matches(isDisplayed()))
    }

    @Test
    fun websiteTextViewIsVisible(){
        onView(withId(R.id.picture_website_textview)).check(matches(isDisplayed()))
    }

    @Test
    fun fabIsVisible(){
        onView(withId(R.id.set_background_button)).check(matches(isDisplayed()))
    }

    @Test
    fun wallpaperPickerIsDisplayedTest() {
        val floatingActionButton = onView(
            Matchers.allOf(
                withId(R.id.set_background_button),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(ViewActions.click())
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