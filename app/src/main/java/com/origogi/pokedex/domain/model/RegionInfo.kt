package com.origogi.pokedex.domain.model

enum class RegionInfo(val regionName: String, val regionId: Int) {
    KANTO("Kanto", 1),
    JOHTO("Johto", 2),
    HOENN("Hoenn", 3),
    SINNOH("Sinnoh", 4),
    UNOVA("Unova", 5),
    KALOS("Kalos", 6),
    ALOLA("Alola", 7),
}

val RegionInfo.backgroundImageUrl : String
    get() = "file:///android_asset/region_${regionName.lowercase()}.png"