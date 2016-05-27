package com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.dagger

import com.wojciechmaciejewski.githubapirequester.presenters.user_details.UserDetail

/**
 *
 */
interface UserDetailModule {

    fun provideUserDetailPresenter(): UserDetail.Presenter
}