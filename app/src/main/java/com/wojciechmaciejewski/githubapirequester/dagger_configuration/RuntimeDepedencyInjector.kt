package com.wojciechmaciejewski.githubapirequester.dagger_configuration

import com.wojciechmaciejewski.githubapirequester.AskActivity

/**
 *
 */



class RuntimeDepedencyInjector:DepedencyInjector{
    override fun inject(askActivity: AskActivity) {
        throw UnsupportedOperationException()
    }
}