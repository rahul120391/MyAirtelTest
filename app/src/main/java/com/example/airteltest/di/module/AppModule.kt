package com.example.airteltest.di.module

import android.content.Context
import androidx.room.Room
import com.example.airteltest.app.MyApplication
import com.example.airteltest.database.DatabaseConstants.DATABASE_NAME
import com.example.airteltest.database.MyDatabase
import com.example.airteltest.network.baseusecase.UseCaseHandler
import com.example.airteltest.network.retrofit.RetrofitClient
import com.example.airteltest.network.retrofit.RetrofitServiceAnnotator
import com.example.airteltest.utils.Utility
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideRetrofitServiceAnnotator(): RetrofitServiceAnnotator =
        RetrofitClient.createRetrofitService()

    @Provides
    @Singleton
    internal fun provideUseCaseHandlerInstance() = UseCaseHandler.getInstance()

    @Provides
    @Singleton
    internal fun provideUtils(context: MyApplication):Utility= Utility.getInstance(context)

    @Provides
    @Singleton
    internal fun provideDatabase(context:MyApplication):MyDatabase =
        Room.databaseBuilder(context,MyDatabase::class.java,DATABASE_NAME)
            .build()
}