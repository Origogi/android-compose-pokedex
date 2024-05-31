package com.origogi.pokedex.di


import com.origogi.pokedex.data.repository.PokemonInfoRepositoryImpl
import com.origogi.pokedex.data.repository.PokemonSpeciesInfoRepositoryImpl
import com.origogi.pokedex.data.repository.PokemonWeaknessTypesRepositoryImpl
import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.repository.PokemonInfoRepository
import com.origogi.pokedex.domain.repository.PokemonSpeciesInfoRepository
import com.origogi.pokedex.domain.repository.PokemonWeaknessTypesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providePokemonDetailInfoRepository(client : PokedexApiClient): PokemonInfoRepository {
        return PokemonInfoRepositoryImpl(
            client
        )
    }

    @Provides
    @Singleton
    fun providePokemonSpeciesInfoRepository(client: PokedexApiClient): PokemonSpeciesInfoRepository {
        return PokemonSpeciesInfoRepositoryImpl(client)
    }

    @Provides
    @Singleton
    fun providePokemonWeaknessTypesRepository(client: PokedexApiClient): PokemonWeaknessTypesRepository {
        return PokemonWeaknessTypesRepositoryImpl(client)
    }

}