package com.tods.giphy_project.di

import android.content.Context
import androidx.room.Room
import com.tods.giphy_project.data.local.GiphyDatabase
import com.tods.giphy_project.data.remote.GiphyAPI
import com.tods.giphy_project.util.Constants.BASE_URL
import com.tods.giphy_project.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Singleton
    @Provides
    fun providesGiphyDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, GiphyDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providesGiphyDAO(database: GiphyDatabase) = database.giphyDao()

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideServiceApi(retrofit: Retrofit): GiphyAPI {
        return retrofit.create(GiphyAPI::class.java)
    }
}