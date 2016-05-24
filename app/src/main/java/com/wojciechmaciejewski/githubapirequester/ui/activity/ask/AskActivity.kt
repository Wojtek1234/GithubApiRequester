package com.wojciechmaciejewski.githubapirequester.ui.activity.ask


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.jakewharton.rxbinding.widget.RxTextView
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import com.wojciechmaciejewski.githubapirequester.presenters.ask.Ask
import com.wojciechmaciejewski.githubapirequester.ui.AbstractActivity
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses.AskElementsRecyclerAdapter
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

    private val adapter = AskElementsRecyclerAdapter()
    private lateinit var subsription: Subscription;

    override fun onInitializeInjection() {
        this.dependenciesInjector.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)
        askElementRecyclerView.adapter = adapter
        askElementRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        subsription = RxTextView.textChanges(titleMessageEditText).debounce (sleepBeforeTriggerApiCall, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { if (it.length != 0) presenter.loadResults(it.toString()) }

    }

    override fun fillUpElements(list: List<AskElement>) {
        adapter.changeElements(list)
    }

    override fun handleError(error: Throwable) {
        Log.e("ERROR", error.message)
    }

    override fun addElements(list: List<AskElement>) {
        adapter.changeElements(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        subsription.unsubscribe()
        presenter.clearSubscriptions()
    }
}
