package com.wojciechmaciejewski.githubapirequester.model.dto

import android.support.annotation.IntDef

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**

 */
abstract class AskElement : AskElementInterface {


    @IntDef(USER_TYPE.toLong(), REPO_TYPE.toLong())
    @Retention(RetentionPolicy.SOURCE)
    annotation class ElementType

    @ElementType
    var elementType: Int = 0
        internal set


    companion object {
        const val USER_TYPE = 0
        const val REPO_TYPE = 1
    }


}
