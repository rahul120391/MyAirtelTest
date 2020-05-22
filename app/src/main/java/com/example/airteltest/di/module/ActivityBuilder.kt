package com.example.airteltest.di.module

import com.example.airteltest.search.di.SearchModule
import com.example.airteltest.search.views.activity.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [SearchModule::class])
    internal abstract fun provideSearchActivityModules():SearchActivity
}