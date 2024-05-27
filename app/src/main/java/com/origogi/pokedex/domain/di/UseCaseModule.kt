package com.origogi.pokedex.domain.di

import com.origogi.pokedex.domain.repository.PokemonCardInfoListRepository
import com.origogi.pokedex.domain.usecase.GetPokemonCardInfoListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPokemonCardInfoListUseCase(pokemonCardInfoListRepository: PokemonCardInfoListRepository): GetPokemonCardInfoListUseCase {
        return GetPokemonCardInfoListUseCase(pokemonCardInfoListRepository)
    }
}