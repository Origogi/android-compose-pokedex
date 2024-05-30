package com.origogi.pokedex.di


import com.origogi.pokedex.data.repository.PokemonInfoRepositoryImpl
import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.repository.PokemonInfoRepository
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

}