package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger

import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.AskActivity

/**
 *
 */
interface AskActivityComponent {
    fun inject(askActivity: AskActivity)
}