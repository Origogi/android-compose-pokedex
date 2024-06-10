package com.origogi.pokedex.di


import com.origogi.pokedex.data.repository.PokemonEvolutionChainInfoRepositoryImpl
import com.origogi.pokedex.data.repository.PokemonFavoriteRepositoryImpl
import com.origogi.pokedex.data.repository.PokemonInfoRepositoryImpl
import com.origogi.pokedex.data.repository.PokemonSpeciesInfoRepositoryImpl
import com.origogi.pokedex.data.repository.PokemonWeaknessTypesRepositoryImpl
import com.origogi.pokedex.domain.repository.PokemonEvolutionChainInfoRepository
import com.origogi.pokedex.domain.repository.PokemonFavoriteRepository
import com.origogi.pokedex.domain.repository.PokemonInfoRepository
import com.origogi.pokedex.domain.repository.PokemonSpeciesInfoRepository
import com.origogi.pokedex.domain.repository.PokemonWeaknessTypesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindPokemonInfoRepository(repository: PokemonInfoRepositoryImpl): PokemonInfoRepository

    @Binds
    @Singleton
    fun bindPokemonSpeciesInfoRepository(repository: PokemonSpeciesInfoRepositoryImpl): PokemonSpeciesInfoRepository

    @Binds
    @Singleton
    fun bindPokemonWeaknessTypesRepository(repository: PokemonWeaknessTypesRepositoryImpl): PokemonWeaknessTypesRepository

    @Binds
    @Singleton
    fun bindPokemonFavoriteRepository(repository: PokemonFavoriteRepositoryImpl): PokemonFavoriteRepository

    @Binds
    @Singleton
    fun bindPokemonEvolutionChainInfoRepository(repository: PokemonEvolutionChainInfoRepositoryImpl): PokemonEvolutionChainInfoRepository

}