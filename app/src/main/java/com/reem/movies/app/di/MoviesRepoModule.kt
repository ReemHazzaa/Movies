package com.reem.movies.app.di


import com.reem.movies.data.repo.MoviesRepoImpl
import com.reem.movies.domain.repo.MoviesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesRepoModule {
    @Binds
    abstract fun provideMoviezRepoModule(moviezRepoModuleImp: MoviesRepoImpl): MoviesRepo
}