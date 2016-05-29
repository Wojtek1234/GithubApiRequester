package com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail

import android.content.res.ColorStateList
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.graphics.Palette
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUserDetail
import com.wojciechmaciejewski.githubapirequester.presenters.user_details.UserDetail
import com.wojciechmaciejewski.githubapirequester.ui.AbstractActivity
import com.wojciechmaciejewski.githubapirequester.utils.ErrorHandler
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
        fab!!.setOnClickListener { view -> Snackbar.make(view, "Authentication is not yet implemented", Snackbar.LENGTH_LONG).setAction("Action", null).show() }
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearSubscriptions()
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
        followers.forEach {
            val view = layoutInflater.inflate(R.layout.askelement_viewholder, linearForFollowers, false)
            fillUpLayout(it, view)
            linearForFollowers.addView(view)
        }
    }

    private fun fillUpLayout(it: GithubUser, view: View) {
        (view.findViewById(R.id.askElementId) as TextView).text = it.id.toString()
        (view.findViewById(R.id.askElementHomeUrl) as TextView).text = it.homepage
        (view.findViewById(R.id.askElementTitle) as TextView).text = it.login
        doAfterLollipop { view.findViewById(R.id.repoUserImageView).transitionName = "" }
        picasso.load(it.imageUrl).placeholder(R.drawable.user_icon)
                .into(view.findViewById(R.id.repoUserImageView) as ImageView)
    }

    override fun handleError(error: Throwable) {
        ErrorHandler.createErrorDialog(this, error)
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
