package com.wojciechmaciejewski.githubapirequester.model.dto



/**
 *
 */


interface AskElementInterface {
    val id:Long;
    fun returnTitle(): String
    fun returnHomepage(): String
    fun returnImageId(): Int
}