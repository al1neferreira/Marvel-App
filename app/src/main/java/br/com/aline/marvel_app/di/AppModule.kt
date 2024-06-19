package br.com.aline.marvel_app.di

import br.com.aline.marvel_app.data.data_source.MarvelApi
import br.com.aline.marvel_app.data.data_source.repository.MarvelRepositoryImpl
import br.com.aline.marvel_app.domain.repository.MarvelRepository
import br.com.aline.marvel_app.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton

    fun provideMarvelApi():MarvelApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMarvelRepository(api: MarvelApi):MarvelRepository{
        return MarvelRepositoryImpl(api)

    }


}