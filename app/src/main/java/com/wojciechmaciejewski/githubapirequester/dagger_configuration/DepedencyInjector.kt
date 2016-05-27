package com.wojciechmaciejewski.githubapirequester.dagger_configuration

import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.AskActivity
import com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.UserDetailActivity

/**
 *
 */


interface DepedencyInjector{
    fun inject(askActivity: AskActivity);
    fun inject(userDetailActivity: UserDetailActivity)
}