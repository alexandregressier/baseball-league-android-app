package dev.gressier.abl.teams

import dev.gressier.abl.teams.Team.Division.*

typealias TeamId = String

data class Team(
    val id: TeamId,
    val city: String,
    val nickname: String,
    val division: Division,
    val wins: Int = 0,
    val losses: Int = 0,
    val leagueRank: Int = -1,
    val divisionRank: Int = -1,
) {
    enum class Division { EAST, WEST, UNKNOWN }

    companion object {
        val Appleton = Team("APL", "Appleton", "Foxes", EAST)
        val Baraboo = Team("BOO", "Baraboo", "Big Tops", WEST)
        val EauClaire = Team("EC", "Eau Claire", "Blues", WEST)
        val GreenBay = Team("GB", "Green Bay", "Skyline", EAST)
        val LaCrosse = Team("LAX", "La Crosse", "Lovers", WEST)
        val LakeDelton = Team("LD", "Lake Delton", "Breakers", WEST)
        val Madison = Team("MSN", "Madison", "Snowflakes", WEST)
        val Milwaukee = Team("MKE", "Milwaukee", "Sunrise", EAST)
        val Pewaukee = Team("PKE", "Pewaukee", "Princesses", EAST)
        val Shawano = Team("SHW", "Shawano", "Shorebirds", EAST)
        val SpringGreen = Team("SG", "Spring Green", "Thespians", WEST)
        val SturgeonBay = Team("SB", "Sturgeon Bay", "Elders", EAST)
        val Waukesha = Team("WAU", "Waukesha", "Riffs", EAST)
        val WisconsinRapids = Team("WR", "Wisconsin Rapids", "Cranberries", WEST)
    }
}