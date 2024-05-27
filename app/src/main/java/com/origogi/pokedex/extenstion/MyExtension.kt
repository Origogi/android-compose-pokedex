package com.origogi.pokedex.extenstion
fun <T> List<T>.sample(n: Int): List<T> {
    return this.shuffled().take(n)
}