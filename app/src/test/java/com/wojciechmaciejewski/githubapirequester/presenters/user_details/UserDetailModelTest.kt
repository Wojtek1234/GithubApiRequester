package com.wojciechmaciejewski.githubapirequester.presenters.user_details

import com.wojciechmaciejewski.githubapirequester.UnitTest
import com.wojciechmaciejewski.githubapirequester.network.GithubUserDetailApi
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**

 */
class UserDetailModelTest : UnitTest() {

    @Mock lateinit var githubApi: GithubUserDetailApi
    lateinit var model: UserDetail.Model
    override fun onSetup() {
        model = UserDetailModel(githubApi)
    }

    @Test
    fun testLoadDataForUser() {
        val userName = "grzegprzBrzeczyszczykiewicz"
        model.loadDataForUser(userName)
        Mockito.verify(githubApi).getUserDetails(userName)
    }

    @Test
    fun testLoadUserFollowers() {
        val userName = "grzegprzBrzeczyszczykiewicz"
        model.loadUserFollowers(userName)
        Mockito.verify(githubApi).getUserFollowers(userName)
    }
}