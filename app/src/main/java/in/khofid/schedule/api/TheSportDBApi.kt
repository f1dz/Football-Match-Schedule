package `in`.khofid.schedule.api

import `in`.khofid.schedule.BuildConfig

object TheSportDBApi {

    const val ID_LEAGUE = 4328

    fun uriCommon(): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"
    }

    fun getLastMatch(): String {
        return uriCommon() + "/eventspastleague.php?id=" + ID_LEAGUE.toString()
    }

    fun getNextMatch(): String {
        return uriCommon() + "/eventsnextleague.php?id=" + ID_LEAGUE.toString()
    }

    fun getMatchDetail(matchId: Int?): String {
        return uriCommon() + "/lookupevent.php?id=" + matchId.toString()
    }

    fun getTeamDetail(teamId: Int): String {
        return uriCommon() + "/lookupteam.php?id=" + teamId.toString()
    }

    fun getTeams(league: String?): String {
        return uriCommon() + "/search_all_teams.php?l=" + league
    }

    fun getTeamDetail(teamId: String?) = uriCommon() + "/lookupteam.php?id=" + teamId

}