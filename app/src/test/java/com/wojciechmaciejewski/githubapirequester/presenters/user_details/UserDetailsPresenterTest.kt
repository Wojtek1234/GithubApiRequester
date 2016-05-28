package com.wojciechmaciejewski.githubapirequester.presenters.user_details

import com.wojciechmaciejewski.githubapirequester.UnitTest
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUserDetail
import com.wojciechmaciejewski.githubapirequester.utils.MySchedulers
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import rx.Observable
import rx.schedulers.Schedulers
import rx.subjects.PublishSubject

/**

 */
class UserDetailsPresenterTest : UnitTest() {

    @Mock lateinit var model: UserDetail.Model
    @Mock lateinit var view: UserDetail.View
    val NUMBER = 10
    lateinit var presenter: UserDetail.Presenter
    override fun onSetup() {
        presenter = UserDetailsPresenter(model, view, MySchedulers(Schedulers.immediate(), Schedulers.immediate()))
    }

    @Test
    fun testLoadUserData() {
        val queryUserName = "grzegorzBrzeczyszczykiewicz"
        Mockito.`when`(model.loadDataForUser(queryUserName)).thenReturn(Observable.just(createDetailUser(123, queryUserName)))
        Mockito.`when`(model.loadUserFollowers(queryUserName)).thenReturn(Observable.just(createListOfGithubUsers()))
        presenter.loadUserData(queryUserName)
        Mockito.verify(view).fillUpHeaderView(createDetailUser(123, queryUserName))
        Mockito.verify(view).fillUpFollowers(createListOfGithubUsers())

    }


    @Test
    fun testClearSubscriptions() {
        val query = "pig"
        val subject: PublishSubject<GithubUserDetail>
        subject = PublishSubject.create()

        val subjectFollowers: PublishSubject<List<GithubUser>>
        subjectFollowers = PublishSubject.create()
        Mockito.`when`(model.loadDataForUser(Mockito.anyString())).thenReturn(subject)
        Mockito.`when`(model.loadUserFollowers(Mockito.anyString())).thenReturn(subjectFollowers)
        presenter.loadUserData(query)
        subject.onNext(createDetailUser(1, query))
        subjectFollowers.onNext(createListOfGithubUsers())
        presenter.clearSubscriptions()
        subject.onNext(createDetailUser(1, query))
        subjectFollowers.onNext(createListOfGithubUsers())
        subject.onNext(createDetailUser(1, query))
        subjectFollowers.onNext(createListOfGithubUsers())

        Mockito.verify(view, Mockito.atMost(1)).fillUpFollowers(createListOfGithubUsers())
        Mockito.verify(view, Mockito.atMost(1)).fillUpHeaderView(createDetailUser(1, query))

    }

    @Test
    fun testHandleError() {
        val query = "pig"
        val throws = RuntimeException()
        Mockito.`when`(model.loadDataForUser(Mockito.anyString())).thenReturn(Observable.error(throws))
        Mockito.`when`(model.loadUserFollowers(Mockito.anyString())).thenReturn(Observable.error(throws))
        presenter.loadUserData(query)
        Mockito.verify(view, Mockito.times(2)).handleError(throws)


    }

    fun createDetailUser(id: Int, mquery: String) = GithubUserDetail(id, mquery, "url $mquery", "image $mquery", false, mquery, "location $mquery", "email  $mquery", 123, 42, 124)
    fun createListOfGithubUsers() = (0..NUMBER - 1).map { GithubUser(it, "login $it", "url $it", "imageurl $it") }
}