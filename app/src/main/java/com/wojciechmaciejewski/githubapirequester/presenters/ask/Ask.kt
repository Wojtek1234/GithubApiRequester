package com.wojciechmaciejewski.githubapirequester.presenters.ask

import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import com.wojciechmaciejewski.githubapirequester.presenters.ClearSubscribtions
import rx.Observable

/**
 *
 */
interface Ask {

    interface View {
        fun fillUpElements(list: List<AskElement>)
    }

    interface Model {
        fun getAskResult(query: String, page: Int?): Observable<List<AskElement>>
    }

    interface Presenter : ClearSubscribtions {
        fun loadResults(query: String)
    }
}