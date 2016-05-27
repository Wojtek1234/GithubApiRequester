package com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser
import com.wojciechmaciejewski.githubapirequester.presenters.user_details.UserDetail
import com.wojciechmaciejewski.githubapirequester.ui.AbstractActivity
import com.wojciechmaciejewski.githubapirequester.utils.USERNAME_IMAGE_KEY
import com.wojciechmaciejewski.githubapirequester.utils.USERNAME_KEY
import kotlinx.android.synthetic.main.activity_user_detail.*
import pl.stsg.e_learning.extension.doAfterLollipop
import javax.inject.Inject

class UserDetailActivity : AbstractActivity(), UserDetail.View {


    @Inject lateinit var picasso: Picasso
    private lateinit var userName: String
    private lateinit var userImageUrl: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        handleImageTransition()
        collapsingToolbarLayout.title = userName;
        collapsingToolbarLayout.setExpandedTitleColor(resources.getColor(android.R.color.transparent));


        val fab = findViewById(R.id.fab) as FloatingActionButton?
        fab!!.setOnClickListener { view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show() }
    }

    private fun handleImageTransition() {
        doAfterLollipop { postponeEnterTransition() }
        userImageUrl = intent.getStringExtra(USERNAME_IMAGE_KEY)
        userName = intent.getStringExtra(USERNAME_KEY)
        loadPicture(userImageUrl)
    }

    override fun onInitializeInjection() {
        this.dependenciesInjector.inject(this)
    }

    override fun fillUpHeaderView() {
        throw UnsupportedOperationException()
    }

    override fun fillUpFollowers(followers: List<GithubUser>) {
        throw UnsupportedOperationException()
    }

    private fun loadPicture(url: String) {
        picasso.load(url)
                .into(userImageView, object : Callback {
                    override fun onSuccess() {
                        doAfterLollipop({ startPostponedEnterTransition() })
                    }

                    override fun onError() {
                        doAfterLollipop({ startPostponedEnterTransition() })
                    }
                })
    }
}
