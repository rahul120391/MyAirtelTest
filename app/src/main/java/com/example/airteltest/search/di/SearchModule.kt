package com.example.airteltest.search.di

import com.example.airteltest.database.MyDatabase
import com.example.airteltest.network.baseusecase.UseCaseHandler
import com.example.airteltest.network.retrofit.RetrofitServiceAnnotator
import com.example.airteltest.search.datasource.LocalSearchDataSource
import com.example.airteltest.search.datasource.RemoteSearchDataSource
import com.example.airteltest.search.repository.SearchRepository
import com.example.airteltest.search.usecase.SearchUseCase
import com.example.airteltest.search.viewmodel.SearchViewModel
import com.example.airteltest.utils.Utility
import dagger.Module
import dagger.Provides


@Module
class SearchModule {



    @Provides
    fun provideLocalSearchDataSource(myDatabase: MyDatabase):LocalSearchDataSource=LocalSearchDataSource(myDatabase)


    @Provides
    fun provideRemoteSearchDataSource(retrofitServiceAnnotator: RetrofitServiceAnnotator):RemoteSearchDataSource=RemoteSearchDataSource(retrofitServiceAnnotator)

    @Provides
    fun provideSearchRepository(localSearchDataSource: LocalSearchDataSource,remoteSearchDataSource: RemoteSearchDataSource)=SearchRepository(localSearchDataSource,remoteSearchDataSource)

    @Provides
    fun provideSearchUseCase(searchRepository: SearchRepository):SearchUseCase= SearchUseCase(searchRepository)

    @Provides
    fun provideSearchViewModel(searchUseCase: SearchUseCase,useCaseHandler: UseCaseHandler,utility: Utility)=SearchViewModel(searchUseCase,useCaseHandler,utility)
}