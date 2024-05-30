package com.origogi.pokedex.di

import com.origogi.pokedex.domain.repository.PokemonInfoRepository
import com.origogi.pokedex.domain.usecase.GetPokemonCardInfoListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPokemonCardInfoListUseCase(
        repository: PokemonInfoRepository
    ): GetPokemonCardInfoListUseCase {
        return GetPokemonCardInfoListUseCase(repository)
    }

}