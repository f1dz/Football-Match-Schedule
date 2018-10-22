package `in`.khofid.schedule.api

import `in`.khofid.schedule.BuildConfig
import android.net.Uri

object TheSportDBApi {

    const val ID_LEAGUE = 4328

    fun uriCommon(): Uri.Builder {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
    }

    fun getLastMatch(): String {
        return uriCommon()
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", ID_LEAGUE.toString())
            .build()
            .toString()
    }

    fun getNextMatch(): String {
        return uriCommon()
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", ID_LEAGUE.toString())
            .build()
            .toString()
    }

    fun getMatchDetail(matchId: Int?): String {
        return uriCommon()
            .appendPath("lookupevent.php")
            .appendQueryParameter("id", matchId.toString())
            .build()
            .toString()
    }

    fun getTeamDetail(teamId: Int): String{
        return uriCommon()
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", teamId.toString())
            .build()
            .toString()
    }
}