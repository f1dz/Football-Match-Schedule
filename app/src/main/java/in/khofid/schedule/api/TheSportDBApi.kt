package `in`.khofid.schedule.api

import `in`.khofid.schedule.BuildConfig
import android.net.Uri

object TheSportDBApi {

    const val ID_LEAGUE = 4328

    fun getLastMatch(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", ID_LEAGUE.toString())
            .build()
            .toString()
    }

    fun getNextMatch(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", ID_LEAGUE.toString())
            .build()
            .toString()
    }
}