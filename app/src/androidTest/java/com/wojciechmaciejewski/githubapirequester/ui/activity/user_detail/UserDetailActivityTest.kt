package com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail

import android.content.Intent
import android.support.design.widget.CollapsingToolbarLayout
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUserDetail
import com.wojciechmaciejewski.githubapirequester.presenters.user_details.UserDetail
import com.wojciechmaciejewski.githubapirequester.utils.USERNAME_IMAGE_KEY
import com.wojciechmaciejewski.githubapirequester.utils.USERNAME_KEY
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**

 */
@RunWith(AndroidJUnit4::class)
class UserDetailActivityTest {

    @get:Rule val activityRule = ActivityTestRule(UserDetailActivity::class.java
            , true, false)

    companion object {
        val id = 1553

        val homepage = "url"
        val imageUrl = "muasl"
        val name = "Grzegorz Brzeczyszczykiewicz"
        val location = "Chrząszczyżewoszyce, powiat Łękołody"
        val email = "grzegorz.brzeczyszczykiewicz@gmail.com"
        val publicReposNumber = 17
        val followers = 25
        val following = 127
        var testCase = 0
    }

    val userName = "my_user"
    val userImageUrl = "user_image_url"
    @Before
    fun setUp() {


        testCase = 0

    }

    @Test
    fun testSetUpCorrectThingsFromIntent() {
        startActivity()
        matchToolbarTitle(userName)

    }

    @Test
    fun testFillUpUserDetailData() {
        testCase = 1
        startActivity()
        onView(withId(R.id.userDetailNameText)).check(matches(withText(name)))
        onView(withId(R.id.userDetailLocationText)).check(matches(withText(location)))
        onView(withId(R.id.userDetailEmailText)).check(matches(withText(email)))
        onView(withId(R.id.userDetailPublicReposText)).check(matches(withText("$publicReposNumber")))
        onView(withId(R.id.userDetailFollowersText)).check(matches(withText("$followers")))
        onView(withId(R.id.userDetailFollowingText)).check(matches(withText("$following")))

    }

    private fun startActivity() {
        val intent = Intent()
        intent.putExtra(USERNAME_KEY, userName)
        intent.putExtra(USERNAME_IMAGE_KEY, userImageUrl)
        activityRule.launchActivity(intent)
    }

    class TestUserDetailPresenter(val view: UserDetail.View) : UserDetail.Presenter {


        override fun loadUserData(userName: String) {
            when (testCase) {
                1 -> {
                    view.fillUpHeaderView(GithubUserDetail(1, userName, homepage, imageUrl, false, name, location, email, publicReposNumber, followers, following))
                }
            }

        }
    }

    //Variation about https://github.com/chiuki/espresso-samples/blob/master/toolbar-title/app/src/androidTest/java/com/sqisland/espresso/toolbar_title/MainActivityTest.java
    private fun matchToolbarTitle(title: CharSequence): ViewInteraction {
        return onView(isAssignableFrom(CollapsingToolbarLayout::class.java)).check(matches(withToolbarTitle(`is`(title))))
    }

    private fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<Any> {
        return object : BoundedMatcher<Any, CollapsingToolbarLayout>(CollapsingToolbarLayout::class.java) {
            override fun matchesSafely(toolbar: CollapsingToolbarLayout): Boolean {
                return textMatcher.matches(toolbar.title)
            }

            override fun describeTo(description: Description) {
                description.appendText("with toolbar title: ")
                textMatcher.describeTo(description)
            }
        }
    }
}