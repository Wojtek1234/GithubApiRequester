package com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.dagger

import com.wojciechmaciejewski.githubapirequester.presenters.user_details.UserDetail
import com.wojciechmaciejewski.githubapirequester.presenters.user_details.UserDetailModel
import com.wojciechmaciejewski.githubapirequester.utils.MySchedulers

/**
 *
 */
interface UserDetailModule {

    fun provideUserDetailPresenter(model: UserDetailModel, mySchedulers: MySchedulers): UserDetail.Presenter
}