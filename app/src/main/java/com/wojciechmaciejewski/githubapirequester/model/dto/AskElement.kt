package com.wojciechmaciejewski.githubapirequester.model.dto

import android.support.annotation.IntDef

/**
 *
 */


interface AskElement {
    val id:Long;
    fun returnTitle(): String
    fun returnHomepage(): String
}