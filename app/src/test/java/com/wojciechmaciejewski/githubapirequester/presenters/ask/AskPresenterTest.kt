package com.wojciechmaciejewski.githubapirequester.presenters.ask

import com.wojciechmaciejewski.githubapirequester.UnitTest
import com.wojciechmaciejewski.githubapirequester.createListOfAskElements
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import pl.stsg.e_learning.helpers.rxSchedulers.MySchedulers
import rx.Observable
import rx.schedulers.Schedulers
import rx.subjects.PublishSubject

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

        presenter.loadResults(query)
        Mockito.verify(view).fillUpElements(createListOfAskElements(query, 2).sortedBy { it.id })
        presenter.loadResults(query)
        Mockito.verify(model).getAskResult(query, 2)

    }

    @Test
    fun testClearSubscriptions() {
        val query = "pig"
        val subject: PublishSubject<List<AskElement>>
        subject = PublishSubject.create()
        Mockito.`when`(model.getAskResult(query, Mockito.anyInt())).thenReturn(subject)
        presenter.loadResults(query)
        subject.onNext(createListOfAskElements(query, 1))
        Mockito.verify(view).fillUpElements(createListOfAskElements(query, 1).sortedBy { it.id })
        presenter.clearSubscriptions()
        subject.onNext(createListOfAskElements(query, 5))
        Mockito.verify(view, Mockito.never()).fillUpElements(createListOfAskElements(query, 5).sortedBy { it.id })

    }
}