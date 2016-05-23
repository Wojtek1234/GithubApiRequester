package com.wojciechmaciejewski.githubapirequester.ui.activity.ask


import android.os.Bundle
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import com.wojciechmaciejewski.githubapirequester.model.dto.GithubRepoAskElement
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser

import com.wojciechmaciejewski.githubapirequester.ui.AbstractActivity

class AskActivity : AbstractActivity() {

    override fun onInitializeInjection() {
        this.dependenciesInjector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)
    }
}
