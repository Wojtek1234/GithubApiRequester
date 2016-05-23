package com.wojciechmaciejewski.githubapirequester.presenters.ask

import pl.stsg.e_learning.helpers.rxSchedulers.MySchedulers

/**
 *
 */


class AskPresenter(val askModel: Ask.Model, val askView: Ask.View, val mySchedulers: MySchedulers) : Ask.Presenter {


    private var currentQuery: String? = null
    private var counter = 0;
    override fun loadResults(query: String) {
        if (currentQuery == query) {
            counter++
        } else {
            currentQuery = query;
            counter = 1
        }
    }

    override fun clearSubscriptions() {
        throw UnsupportedOperationException()
    }
}