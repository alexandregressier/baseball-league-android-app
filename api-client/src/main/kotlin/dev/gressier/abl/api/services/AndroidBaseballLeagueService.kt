package dev.gressier.abl.api.services

import dev.gressier.abl.api.models.*
import dev.gressier.abl.api.models.enums.NotificationTypeApiModel
import dev.gressier.abl.api.models.enums.PositionApiModel
import dev.gressier.abl.api.typealiases.*
import retrofit2.http.*

interface AndroidBaseballLeagueService {

    @GET("teams")
    suspend fun getTeams(): List<TeamApiModel>

    @GET("games")
    suspend fun getGames(
        @Query("currentDateTime") currentDateTime: DateTime? = null,
        @Query("requestedDate") requestedDate: Date? = null,
        @Query("teamId") teamId: TeamId? = null,
    ): List<ScheduledGameApiModel>

    @GET("players")
    suspend fun getPlayers(
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("query") query: String? = null,
        @Query("teamId") teamId: TeamId? = null,
        @Query("position") position: PositionApiModel? = null,
        @Query("isPitcher") isPitcher: Boolean? = null,
        @Query("isOutfielder") isOutfielder: Boolean? = null,
    ): List<PlayerApiModel>

    @GET("players/{playerId}")
    suspend fun getPlayer(
        @Path("playerId") playerId: PlayerId,
        @Query("currentDate") currentDate: Date? = null,
    ): BoxScoreItemsApiModel

    @GET("standings")
    suspend fun getStandings(
        @Query("currentDate") currentDate: Date? = null,
    ): List<TeamStandingApiModel>

    @GET("stats/batting")
    suspend fun getBattingsStats(
        @Query("currentDate") currentDate: Date? = null,
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("sortStat") sortStat: String? = null,
        @Query("sortDescending") sortDescending: Boolean? = null,
        @Query("teamId") teamId: TeamId? = null,
        @Query("position") position: PositionApiModel? = null,
    ): List<BatterBoxScoreItemApiModel>

    @GET("stats/pitching")
    suspend fun getPitchingStats(
        @Query("currentDate") currentDate: Date? = null,
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("sortStat") sortStat: String? = null,
        @Query("sortDescending") sortDescending: Boolean? = null,
        @Query("teamId") teamId: TeamId? = null,
        @Query("position") position: PositionApiModel? = null,
    ): List<PitcherBoxScoreItemApiModel>

    @GET("leaders/batting")
    suspend fun getBattingLeaders(
        @Query("currentDate") currentDate: Date? = null,
    ): List<BatterBoxScoreItemApiModel>

    @GET("leaders/pitching")
    suspend fun getPitchingLeaders(
        @Query("currentDate") currentDate: Date? = null,
    ): List<PitcherBoxScoreItemApiModel>

    @POST("app/notifications")
    suspend fun sendNotificationToPhone(
        @Query("notificationType") notificationType: NotificationTypeApiModel,
        @Query("phoneToken") phoneToken: String,
        @Query("itemId") itemId: ItemId,
        @Query("dataOnly") dataOnly: Boolean = false,
    )

    @GET("app/settings")
    suspend fun getAppSettingsForUser(
        @Query("userId") userId: UserId,
    ): AppSettingsApiModel

    @POST("app/settings")
    suspend fun saveAppSettings(
        @Body settings: AppSettingsApiModel,
    ): AppSettingsApiModel
}