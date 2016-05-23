package com.wojciechmaciejewski.githubapirequester.presenters.ask

import pl.stsg.e_learning.helpers.rxSchedulers.MySchedulers
import rx.Subscription

/**
 *
 */


class AskPresenter(val askModel: Ask.Model, val askView: Ask.View, val mySchedulers: MySchedulers) : Ask.Presenter {


    private var currentQuery: String? = null
    private var counter = 0;
    private var subsribtion: Subscription? = null
    override fun loadResults(query: String) {
        manageQueryParams(query)
        subsribtion = askModel.getAskResult(query, counter)
                .subscribeOn(mySchedulers.subscribeSchedulers)
                .map { it.sortedBy { it.id } }
                .observeOn(mySchedulers.observScheduler)
                .subscribe ({}, {})
    }

    private fun manageQueryParams(query: String) {
        if (currentQuery == query) {
            counter++
        } else {
            currentQuery = query;
            counter = 1
        }
    }

    override fun clearSubscriptions() {
        subsribtion?.unsubscribe()

    }
}