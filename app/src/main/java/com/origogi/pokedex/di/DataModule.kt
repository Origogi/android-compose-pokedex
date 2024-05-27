package com.origogi.pokedex.di

import com.origogi.pokedex.data.repository.FakePokemonCardInfoListRepositoryImpl
import com.origogi.pokedex.domain.repository.PokemonCardInfoListRepository
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
    fun providePokemonCardInfoListRepository(): PokemonCardInfoListRepository {
        return FakePokemonCardInfoListRepositoryImpl()
    }
}