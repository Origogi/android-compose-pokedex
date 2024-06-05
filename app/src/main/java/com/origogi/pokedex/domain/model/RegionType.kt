package com.origogi.pokedex.domain.model

enum class RegionType(val regionName: String, val regionId: Int, val pokedexIdRange: IntRange) {
    KANTO("Kanto", 1, 1..151),
    JOHTO("Johto", 2, 152..251),
    HOENN("Hoenn", 3, 252..386),
    SINNOH("Sinnoh", 4,     387..493),
    UNOVA("Unova", 5, 494..649),
    KALOS("Kalos", 6, 650..721),
    ALOLA("Alola", 7, 722..809),
}

val RegionType.allPokedexIdRange: IntRange
    get() = RegionType.KANTO.pokedexIdRange.first..RegionType.ALOLA.pokedexIdRange.last

val RegionType.backgroundImageUrl : String
    get() = "file:///android_asset/region_${regionName.lowercase()}.png"