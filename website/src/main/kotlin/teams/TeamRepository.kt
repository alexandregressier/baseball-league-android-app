package teams

interface TeamRepository {
    fun getTeams(): List<Team>
}

class TeamRepositoryStatic : TeamRepository {

    override fun getTeams(): List<Team> =
        listOf(
            Team("APL", "Appleton", "Foxes", Team.Division.EAST),
            Team("BOO", "Baraboo", "Big Tops", Team.Division.WEST),
            Team("EC", "Eau Claire", "Blues", Team.Division.WEST),
            Team("GB", "Green Bay", "Skyline", Team.Division.EAST),
            Team("LAX", "La Crosse", "Lovers", Team.Division.WEST),
            Team("LD", "Lake Delton", "Breakers", Team.Division.WEST),
            Team("MSN", "Madison", "Snowflakes", Team.Division.WEST),
            Team("MKE", "Milwaukee", "Sunrise", Team.Division.EAST),
            Team("PKE", "Pewaukee", "Princesses", Team.Division.EAST),
            Team("SHW", "Shawano", "Shorebirds", Team.Division.EAST),
            Team("SG", "Spring Green", "Thespians", Team.Division.WEST),
            Team("SB", "Sturgeon Bay", "Elders", Team.Division.EAST),
            Team("WAU", "Waukesha", "Riffs", Team.Division.EAST),
            Team("WR", "Wisconsin Rapids", "Cranberries", Team.Division.WEST),
        )
}