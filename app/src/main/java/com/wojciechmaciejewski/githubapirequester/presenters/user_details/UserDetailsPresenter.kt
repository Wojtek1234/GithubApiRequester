package com.wojciechmaciejewski.githubapirequester.presenters.user_details

import com.wojciechmaciejewski.githubapirequester.utils.MySchedulers

/**
 *
 */
class UserDetailsPresenter(val model: UserDetail.Model, val view: UserDetail.View, val schedulers: MySchedulers) : UserDetail.Presenter {


    override fun loadUserData(userName: String) {
        model.loadDataForUser(userName)
                .subscribeOn(schedulers.subscribeSchedulers)
                .observeOn(schedulers.observScheduler)
                .subscribe({}, {})
    }


    override fun clearSubscriptions() {
        throw UnsupportedOperationException()
    }
}