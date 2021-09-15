package teams

import teams.Team.Division.EAST
import teams.Team.Division.WEST

interface TeamRepository {
    fun getTeamById(id: TeamId): Team?
    fun getTeams(): List<Team>
}

class TeamRepositoryStatic : TeamRepository {

    override fun getTeamById(id: TeamId): Team? =
        teams.firstOrNull { it.id == id }

    override fun getTeams(): List<Team> =
        teams

    companion object {
        private val teams =
            listOf(
                Team("APL", "Appleton", "Foxes", EAST),
                Team("BOO", "Baraboo", "Big Tops", WEST),
                Team("EC", "Eau Claire", "Blues", WEST),
                Team("GB", "Green Bay", "Skyline", EAST),
                Team("LAX", "La Crosse", "Lovers", WEST),
                Team("LD", "Lake Delton", "Breakers", WEST),
                Team("MSN", "Madison", "Snowflakes", WEST),
                Team("MKE", "Milwaukee", "Sunrise", EAST),
                Team("PKE", "Pewaukee", "Princesses", EAST),
                Team("SHW", "Shawano", "Shorebirds", EAST),
                Team("SG", "Spring Green", "Thespians", WEST),
                Team("SB", "Sturgeon Bay", "Elders", EAST),
                Team("WAU", "Waukesha", "Riffs", EAST),
                Team("WR", "Wisconsin Rapids", "Cranberries", WEST),
            )
    }
}