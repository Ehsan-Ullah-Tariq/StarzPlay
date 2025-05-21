package com.android.starzplay.di.modules

import android.content.Context
import android.os.StrictMode
import com.android.starzplay.data.remote.api.ApiService
import com.android.starzplay.data.repository.MoviesRepositoryImpl
import com.android.starzplay.domain.repository.MoviesRepository
import com.android.starzplay.domain.usecase.SearchUseCase
import com.android.starzplay.utils.localehelper.LanguageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLanguageManager(@ApplicationContext context: Context): LanguageManager {
        return LanguageManager(context)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(repo: MoviesRepository): SearchUseCase =
        SearchUseCase(repo)

    @Provides
    @Singleton
    fun provideMoviesRepository(
        api: ApiService
    ): MoviesRepository =
        MoviesRepositoryImpl(api)

}