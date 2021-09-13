package teams

typealias TeamId = String

data class Team(
    val id: TeamId,
    val city: String,
    val name: String,
    val division: Division,
) {
    enum class Division { EAST, WEST }
}