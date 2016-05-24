package com.wojciechmaciejewski.githubapirequester.ui.activity.ask

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.createListOfAskElementsIn
import com.wojciechmaciejewski.githubapirequester.presenters.ask.Ask
import com.wojciechmaciejewski.githubapirequester.testutils.MyViewAction
import com.wojciechmaciejewski.githubapirequester.testutils.MyViewMatchers
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses.BaseViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**

 */
@RunWith(AndroidJUnit4::class)
class AskActivityTest {
    @get:Rule val activityRule = ActivityTestRule(AskActivity::class.java
            , true, true)
    val id = 123

    companion object {
        var testCase = 0;
        var NUMBER_OF_ELEMENT = 5
        var ADDED_NUMBER = 2
    }


    @Test
    fun testSizeOfAskedElements() {
        Espresso.onView(ViewMatchers.withId(R.id.askElementRecyclerView))
                .check(ViewAssertions
                        .matches(MyViewMatchers
                                .hasRecyclerViewCorrectSize(NUMBER_OF_ELEMENT * 2 + 1)))

    }

    @Test
    fun testLoadMoreOnSwipeDown() {
        Espresso.onView(ViewMatchers.withId(R.id.askElementRecyclerView)).perform(RecyclerViewActions.scrollToPosition<BaseViewHolder>(NUMBER_OF_ELEMENT * 2))
        testCase = 1
        Espresso.onView(ViewMatchers.withId(R.id.askElementRecyclerView)).perform(MyViewAction.swipeDown())
        Espresso.onView(ViewMatchers.withId(R.id.askElementRecyclerView))
                .check(ViewAssertions
                        .matches(MyViewMatchers
                                .hasRecyclerViewCorrectSize(NUMBER_OF_ELEMENT * 2 + 1 + ADDED_NUMBER * 2)))
    }

    class TestAskPresenter(val view: Ask.View) : Ask.Presenter {
        override fun loadResults(query: String) {
            when (testCase) {
                0 -> view.fillUpElements(createListOfAskElementsIn(query, NUMBER_OF_ELEMENT))
                1 -> view.fillUpElements(createListOfAskElementsIn(query, ADDED_NUMBER))
            }
        }

        override fun clearSubscriptions() {
            throw UnsupportedOperationException()
        }
    }
}


