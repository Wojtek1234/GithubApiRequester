package com.wojciechmaciejewski.githubapirequester.presenters.ask

import com.wojciechmaciejewski.githubapirequester.UnitTest
import com.wojciechmaciejewski.githubapirequester.createListOfAskElements
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import pl.stsg.e_learning.helpers.rxSchedulers.MySchedulers
import rx.Observable
import rx.schedulers.Schedulers

/**

 */
class AskPresenterTest : UnitTest() {

    @Mock lateinit var model: Ask.Model
    @Mock lateinit var view: Ask.View


    private lateinit var presenter: Ask.Presenter
    override fun onSetup() {
        presenter = AskPresenter(model, view, MySchedulers(Schedulers.immediate(), Schedulers.immediate()))
    }

    @Test
    fun testLoadResults() {
        val query = "pig"
        Mockito.`when`(model.getAskResult(query, null)).thenReturn(Observable.just(createListOfAskElements(query, 2)))

    }

    @Test
    fun testClearSubsriptions() {

    }
}