package com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail

import android.content.res.ColorStateList
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.graphics.Palette
import android.support.v7.widget.Toolbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUserDetail
import com.wojciechmaciejewski.githubapirequester.presenters.user_details.UserDetail
import com.wojciechmaciejewski.githubapirequester.ui.AbstractActivity
import com.wojciechmaciejewski.githubapirequester.utils.USERNAME_IMAGE_KEY
import com.wojciechmaciejewski.githubapirequester.utils.USERNAME_KEY
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.content_user_detail.*
import pl.stsg.e_learning.extension.doAfterLollipop
import javax.inject.Inject

class UserDetailActivity : AbstractActivity(), UserDetail.View {
    @Inject lateinit var picasso: Picasso
    @Inject lateinit var presenter: UserDetail.Presenter

    private lateinit var userName: String
    private lateinit var userImageUrl: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        handleImageTransition()
        collapsingToolbarLayout.title = userName;
        collapsingToolbarLayout.setExpandedTitleColor(resources.getColor(android.R.color.transparent, null));

        presenter.loadUserData(userName)
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


    override fun fillUpHeaderView(userDetail: GithubUserDetail) {
        userDetailNameText.text = userDetail.name
        userDetailLocationText.text = userDetail.location
        userDetailEmailText.text = userDetail.email
        userDetailPublicReposText.text = "${userDetail.publicReposNumber}"
        userDetailFollowersText.text = "${userDetail.followers}"
        userDetailFollowingText.text = "${userDetail.following}"


    }

    override fun fillUpFollowers(followers: List<GithubUser>) {
        throw UnsupportedOperationException()
    }

    private fun loadPicture(url: String) {
        picasso.load(url)
                .into(userImageView, object : Callback {
                    override fun onSuccess() {
                        val bitmap = (userImageView.drawable as BitmapDrawable).bitmap;
                        Palette.from(bitmap).generate {
                            palette ->
                            setUpColors(palette)
                            doAfterLollipop({ startPostponedEnterTransition() })
                        };

                    }


                    override fun onError() {
                        doAfterLollipop({ startPostponedEnterTransition() })
                    }
                })
    }

    private fun setUpColors(palette: Palette) {
        val primaryDark = resources.getColor(R.color.colorPrimary, null);
        val primary = resources.getColor(R.color.colorPrimaryDark, null);

        val lightVibrantColor = palette.getLightVibrantColor(resources.getColor(android.R.color.white, null));
        val vibrantColor = palette.getVibrantColor(resources.getColor(R.color.colorAccent, null));
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        fab.setRippleColor(lightVibrantColor)
        fab.backgroundTintList = ColorStateList.valueOf(vibrantColor)
    }

}
