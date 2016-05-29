package com.wojciechmaciejewski.githubapirequester.ui.activity.ask


import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.jakewharton.rxbinding.widget.RxTextView
import com.squareup.picasso.Picasso
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.extensions.doAfterBeforeLollipop
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import com.wojciechmaciejewski.githubapirequester.presenters.ask.Ask
import com.wojciechmaciejewski.githubapirequester.ui.AbstractActivity
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses.AskElementsRecyclerAdapter
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses.ProgressViewHolder
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses.RecyclerEndListener
import com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.UserDetailActivity
import com.wojciechmaciejewski.githubapirequester.utils.ErrorHandler
import com.wojciechmaciejewski.githubapirequester.utils.USERNAME_IMAGE_KEY
import com.wojciechmaciejewski.githubapirequester.utils.USERNAME_KEY
import kotlinx.android.synthetic.main.activity_ask.*
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AskActivity : AbstractActivity(), Ask.View {

    companion object {
        var sleepBeforeTriggerApiCall = 500L
    }

    @Inject
    lateinit var presenter: Ask.Presenter

    @Inject
    lateinit var picasso: Picasso


    var loadingFromText = false;


    private val adapter by lazy {
        AskElementsRecyclerAdapter({
            name, imageUrl, pair ->
            doAfterBeforeLollipop({
                val options = ActivityOptions.makeSceneTransitionAnimation(this@AskActivity, pair)
                startActivity(createIntentForUserDetails(imageUrl, name), options.toBundle())
                Log.e("HERE", "HEERRERE")
            }, {
                startActivity(createIntentForUserDetails(imageUrl, name))
            });
        }, picasso)
    }

    private fun createIntentForUserDetails(imageUrl: String?, name: String): Intent {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra(USERNAME_KEY, name)
        intent.putExtra(USERNAME_IMAGE_KEY, imageUrl)
        return intent
    }

    private lateinit var subsription: Subscription;

    override fun onInitializeInjection() {
        this.dependenciesInjector.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)
        manageRecyclerView()
        subsription = RxTextView.textChanges(titleMessageEditText).debounce (sleepBeforeTriggerApiCall, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { doOnTextChanged(it) }

    }

    private fun doOnTextChanged(it: CharSequence) {
        if (it.length != 0) {
            titleMessageEditText.isEnabled = false
            textProgressBar.visibility = View.VISIBLE
            loadingFromText = true
            presenter.loadResults(it.toString())
        } else adapter.clearList()
    }

    private fun manageRecyclerView() {
        askElementRecyclerView.adapter = adapter
        askElementRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        askElementRecyclerView.addOnScrollListener(object : RecyclerEndListener() {
            override fun onLoadMore() {
                if (!loadingFromText) {
                    showProgressBars()
                    presenter.loadResults(titleMessageEditText.text.toString())
                }
            }
        })
    }

    override fun fillUpElements(list: List<AskElement>) {
        loadingFromText = false
        hideProgressBars()
        adapter.changeElements(list)
    }

    override fun handleError(error: Throwable) {
        hideProgressBars()
        ErrorHandler.createErrorDialog(this, error)
    }

    override fun addElements(list: List<AskElement>) {
        hideProgressBars()
        adapter.addToList(list)
    }

    private fun hideProgressBars() {
        titleMessageEditText.isEnabled = true
        setVisible(View.INVISIBLE)
    }

    private fun showProgressBars() {
        setVisible(View.VISIBLE)
    }

    private fun setVisible(visible: Int) {
        val viewHolder = askElementRecyclerView.findViewHolderForAdapterPosition(adapter.itemCount - 1) as ProgressViewHolder?
        viewHolder?.setVisability(visible)
        textProgressBar.visibility = visible
    }

    override fun onDestroy() {
        super.onDestroy()
        subsription.unsubscribe()
        presenter.clearSubscriptions()
    }
}
