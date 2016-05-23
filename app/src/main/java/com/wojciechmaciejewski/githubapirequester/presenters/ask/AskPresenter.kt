package com.wojciechmaciejewski.githubapirequester.presenters.ask

import pl.stsg.e_learning.helpers.rxSchedulers.MySchedulers

/**
 *
 */


class AskPresenter(val askModel: Ask.Model, val askView: Ask.View, val mySchedulers: MySchedulers) : Ask.Presenter {


    override fun loadResults(query: String) {
        throw UnsupportedOperationException()
    }

    override fun clearSubscriptions() {
        throw UnsupportedOperationException()
    }
}