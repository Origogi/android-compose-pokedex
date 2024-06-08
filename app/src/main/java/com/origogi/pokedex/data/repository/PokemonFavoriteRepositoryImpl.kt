package com.origogi.pokedex.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.origogi.pokedex.domain.repository.PokemonFavoriteRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PokemonFavoriteRepositoryImpl @Inject constructor(@ApplicationContext context: Context) :
    PokemonFavoriteRepository {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

    private val currentValues = MutableStateFlow(sharedPreferences.getValues())

    override suspend fun add(pokedexId: Int) = withContext(
        Dispatchers.IO
    ) {

        currentValues.update {
            val newList = currentValues.value + pokedexId
            sharedPreferences.putValue(value = newList)
            newList
        }
    }

    override suspend fun remove(pokedexId: Int) = withContext(
        Dispatchers.IO
    ) {
        currentValues.update {
            val newList = currentValues.value - pokedexId
            sharedPreferences.putValue(value = newList)
            newList
        }
    }

    override fun list(): Flow<List<Int>> = currentValues.asStateFlow()
}

private fun SharedPreferences.putValue(key: String = "favorites", value: List<Int>) {
    edit().apply {
        putString(key, value.joinToString(separator = ","))
        apply()
    }
}

private fun SharedPreferences.getValues(key: String = "favorites"): List<Int> {
    val storedString = getString(key, "")

    if (storedString.isNullOrEmpty()) {
        return emptyList()
    }

    return storedString.split(",").map { it.toInt() }.toList()
}