package com.wojciechmaciejewski.githubapirequester.presenters.user_details

import com.wojciechmaciejewski.githubapirequester.utils.MySchedulers
import rx.Subscription

/**
 *
 */
class UserDetailsPresenter(val model: UserDetail.Model, val view: UserDetail.View, val schedulers: MySchedulers) : UserDetail.Presenter {

    private var subsribtion: Subscription? = null
    private var subsribtionFollowers: Subscription? = null
    override fun loadUserData(userName: String) {
        subsribtion = model.loadDataForUser(userName)
                .subscribeOn(schedulers.subscribeSchedulers)
                .observeOn(schedulers.observScheduler)
                .subscribe({ view.fillUpHeaderView(it) }, { view.handleError(it) })
        subsribtionFollowers = model.loadUserFollowers(userName)
                .subscribeOn(schedulers.subscribeSchedulers)
                .observeOn(schedulers.observScheduler)
                .subscribe({ view.fillUpFollowers(it) }, { view.handleError(it) })
    }


    override fun clearSubscriptions() {
        subsribtionFollowers?.unsubscribe()
        subsribtion?.unsubscribe()
    }
}