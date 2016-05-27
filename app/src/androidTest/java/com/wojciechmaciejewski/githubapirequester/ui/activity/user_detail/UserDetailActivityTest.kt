package com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.wojciechmaciejewski.githubapirequester.presenters.user_details.UserDetail
import org.junit.Rule
import org.junit.runner.RunWith

/**

 */
@RunWith(AndroidJUnit4::class)
class UserDetailActivityTest {

    @get:Rule val activityRule = ActivityTestRule(UserDetailActivity::class.java
            , true, false)


    class TestUserDetailPresenter(val view: UserDetail.View) : UserDetail.Presenter {


        override fun loadUserData(userName: String) {

        }
    }
}