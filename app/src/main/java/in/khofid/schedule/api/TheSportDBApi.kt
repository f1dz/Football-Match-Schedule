package `in`.khofid.schedule.api

import `in`.khofid.schedule.BuildConfig
import `in`.khofid.schedule.utils.encodeUrl

object TheSportDBApi {

    const val ID_LEAGUE = 4328

    fun uriCommon(): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"
    }

    fun getLastMatch(league: String = ID_LEAGUE.toString()): String {
        return uriCommon() + "/eventspastleague.php?id=" + league
    }

    fun getNextMatch(league: String = ID_LEAGUE.toString()): String {
        return uriCommon() + "/eventsnextleague.php?id=" + league
    }

    fun getMatchDetail(matchId: Int?): String {
        return uriCommon() + "/lookupevent.php?id=" + matchId.toString()
    }

    fun getTeamDetail(teamId: Int): String {
        return uriCommon() + "/lookupteam.php?id=" + teamId.toString()
    }

    fun getTeams(league: String?): String {
        return uriCommon() + "/search_all_teams.php?l=" + league?.encodeUrl()
    }

    fun getTeamDetail(teamId: String?) = uriCommon() + "/lookupteam.php?id=" + teamId

    fun getPlayers(teamId: String) = uriCommon() + "/lookup_all_players.php?id=" + teamId

}