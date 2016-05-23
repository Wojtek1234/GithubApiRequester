package com.wojciechmaciejewski.githubapirequester.utils

import rx.Scheduler


data class MySchedulers(val subscribeSchedulers: Scheduler, val observScheduler: Scheduler)
