package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger

import com.wojciechmaciejewski.githubapirequester.presenters.ask.Ask
import com.wojciechmaciejewski.githubapirequester.presenters.ask.AskModel
import com.wojciechmaciejewski.githubapirequester.utils.MySchedulers

/**
 *
 */
interface AskActivityModule {
    fun provideAskPresenter(model: AskModel, mySchedulers: MySchedulers): Ask.Presenter
}