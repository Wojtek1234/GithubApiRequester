package com.wojciechmaciejewski.githubapirequester.presenters.ask

import com.wojciechmaciejewski.githubapirequester.UnitTest
import com.wojciechmaciejewski.githubapirequester.createListOfAskElements
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import com.wojciechmaciejewski.githubapirequester.utils.MySchedulers
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
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
        val query2 = "pig2"
        Mockito.`when`(model.getAskResult(query, 1)).thenReturn(Observable.just(createListOfAskElements(query, 2)))
        Mockito.`when`(model.getAskResult(query, 2)).thenReturn(Observable.just(createListOfAskElements(query, 2)))
        Mockito.`when`(model.getAskResult(query2, 1)).thenReturn(Observable.just(createListOfAskElements(query, 3)))
        presenter.loadResults(query)
        presenter.loadResults(query)
        presenter.loadResults(query2)
        Mockito.verify(view, Mockito.times(1)).fillUpElements(createListOfAskElements(query, 2).sortedBy { it.id })
        Mockito.verify(view, Mockito.times(1)).addElements(createListOfAskElements(query, 2).sortedBy { it.id })
        Mockito.verify(view, Mockito.times(1)).fillUpElements(createListOfAskElements(query, 3).sortedBy { it.id })


    }

    @Test
    fun testClearSubscriptions() {
        val query = "pig"
        val subject: PublishSubject<List<AskElement>>
        subject = PublishSubject.create()
        Mockito.`when`(model.getAskResult(Mockito.anyString(), Mockito.anyInt())).thenReturn(subject)
        presenter.loadResults(query)
        subject.onNext(createListOfAskElements(query, 1))
        Mockito.verify(view).fillUpElements(createListOfAskElements(query, 1).sortedBy { it.id })
        presenter.clearSubscriptions()
        subject.onNext(createListOfAskElements(query, 5))
        Mockito.verify(view, Mockito.never()).fillUpElements(createListOfAskElements(query, 5).sortedBy { it.id })
        Mockito.verify(view, Mockito.never()).addElements(createListOfAskElements(query, 5).sortedBy { it.id })

    }


    @Test
    fun testHandleError() {
        val query = "pig"
        val throws = RuntimeException()
        Mockito.`when`(model.getAskResult(Mockito.anyString(), Mockito.anyInt())).thenReturn(Observable.error(throws))
        presenter.loadResults(query)
        Mockito.verify(view).handleError(throws)


    }


}