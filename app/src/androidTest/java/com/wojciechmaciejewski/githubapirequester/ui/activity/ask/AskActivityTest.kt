package com.wojciechmaciejewski.githubapirequester.ui.activity.ask

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.presenters.ask.Ask
import com.wojciechmaciejewski.githubapirequester.testutils.MyViewMatchers
import com.wojciechmaciejewski.githubapirequester.testutils.createListOfAskElementsIn
import com.wojciechmaciejewski.githubapirequester.testutils.createListOfAskElementsInOfRepo
import com.wojciechmaciejewski.githubapirequester.testutils.createListOfAskElementsInOfUser
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses.AskElementsRecyclerAdapter
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses.BaseViewHolder
import kotlinx.android.synthetic.main.activity_ask.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
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
        val sleepTime: Long = AskActivity.sleepBeforeTriggerApiCall * 2
    }

    @Before
    fun setUp() {
        AskActivity.sleepBeforeTriggerApiCall = 10L
    }

    @After
    fun tearDown() {
        testCase = 0
    }


    @Test
    fun testSizeOfAskedElements() {
        val query = "smok"
        onView(withId(R.id.titleMessageEditText)).perform(ViewActions.typeText(query))
        Thread.sleep(sleepTime)//Time need to handler trigger network call
        onView(withId(R.id.askElementRecyclerView))
                .check(matches(MyViewMatchers
                        .hasRecyclerViewCorrectSize(NUMBER_OF_ELEMENT * 2 + 1)))

    }

    @Test
    fun testSortedWhenAdd() {
        val query = "smok"
        testCase = 1
        onView(withId(R.id.titleMessageEditText)).perform(ViewActions.typeText(query))

        Thread.sleep(sleepTime)//Time need to handler trigger network call
        val listOfAdapterElements = (activityRule.activity.askElementRecyclerView.adapter as AskElementsRecyclerAdapter).listOfElements
        assertEquals(listOfAdapterElements, listOfAdapterElements.sortedBy { it.id })//check is sorted, becuase we cant do it in presenter at the moment.


    }

    @Test
    fun testClearWhenTypeEmpty() {
        val query = "smok"
        onView(withId(R.id.titleMessageEditText)).perform(ViewActions.typeText(query))

        Thread.sleep(sleepTime)//Time need to handler trigger network call
        onView(withId(R.id.askElementRecyclerView))
                .check(matches(MyViewMatchers
                        .hasRecyclerViewCorrectSize(NUMBER_OF_ELEMENT * 2 + 1)))
        onView(withId(R.id.titleMessageEditText)).perform(ViewActions.clearText())
        Thread.sleep(sleepTime)//Time need to handler trigger network call
        onView(withId(R.id.askElementRecyclerView))
                .check(matches(MyViewMatchers
                        .hasRecyclerViewCorrectSize(1)))

    }

    @Test
    fun testClickAndOpenUserDetail() {
        val query = "smok"
        testCase = 2
        onView(withId(R.id.titleMessageEditText)).perform(ViewActions.typeText(query))

        Thread.sleep(sleepTime)//Time need to handler trigger network call
        onView(withId(R.id.askElementRecyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<BaseViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.repoDetailMainLayout)).check(matches(isDisplayed()))

    }

    class TestAskPresenter(val view: Ask.View) : Ask.Presenter {
        override fun loadResults(query: String) {
            when (testCase) {
                0 -> view.fillUpElements(createListOfAskElementsIn(query, NUMBER_OF_ELEMENT))
                1 -> view.addElements(createListOfAskElementsInOfUser(query, ADDED_NUMBER))
                2 -> view.addElements(createListOfAskElementsInOfRepo(query, ADDED_NUMBER))
            }
        }

        override fun clearSubscriptions() {

        }
    }
}


