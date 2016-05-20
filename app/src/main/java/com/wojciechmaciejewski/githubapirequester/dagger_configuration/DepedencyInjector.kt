package com.wojciechmaciejewski.githubapirequester.dagger_configuration

import com.wojciechmaciejewski.githubapirequester.AskActivity

/**
 *
 */


interface DepedencyInjector{
    fun inject(askActivity: AskActivity);
}