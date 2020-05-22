package com.example.airteltest.app

import com.example.airteltest.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MyApplication:DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MyApplication> = DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
    }
}