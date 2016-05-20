package com.wojciechmaciejewski.githubapirequester.dagger_configuration

import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.AskActivity

/**
 *
 */


interface DepedencyInjector{
    fun inject(askActivity: AskActivity);
}