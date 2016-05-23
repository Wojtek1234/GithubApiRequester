package com.wojciechmaciejewski.githubapirequester.ui.activity.ask


import android.os.Bundle
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import com.wojciechmaciejewski.githubapirequester.presenters.ask.Ask
import com.wojciechmaciejewski.githubapirequester.ui.AbstractActivity
import javax.inject.Inject

class AskActivity : AbstractActivity(), Ask.View {


    @Inject
    lateinit var presenter: Ask.Presenter


    override fun onInitializeInjection() {
        this.dependenciesInjector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)
    }

    override fun fillUpElements(list: List<AskElement>) {
        throw UnsupportedOperationException()
    }

    override fun handleError(error: Throwable) {
        throw UnsupportedOperationException()
    }
}
