package com.wojciechmaciejewski.githubapirequester.presenters

/**
 *
 */
interface HandleError {
    fun handleError(error: Throwable, repeatFunction: () -> Unit)
}