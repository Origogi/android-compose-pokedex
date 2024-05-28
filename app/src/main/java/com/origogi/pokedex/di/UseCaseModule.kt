package com.origogi.pokedex.di

import com.origogi.pokedex.domain.repository.PokemonDetailInfoRepository
import com.origogi.pokedex.domain.usecase.GetPokemonCardInfoListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPokemonCardInfoListUseCase(
        repository: PokemonDetailInfoRepository
    ): GetPokemonCardInfoListUseCase {
        return GetPokemonCardInfoListUseCase(repository)
    }

}