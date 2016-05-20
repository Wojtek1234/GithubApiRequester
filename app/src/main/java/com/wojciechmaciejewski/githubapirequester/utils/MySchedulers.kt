package pl.stsg.e_learning.helpers.rxSchedulers

import rx.Scheduler


data class MySchedulers(val subscribeSchedulers: Scheduler,val observScheduler:Scheduler)
