package `in`.khofid.schedule.db

import android.os.Parcelable

data class FavoriteTeam(
    var idTeam: Int? = null,
    var strTeam: String? = null,
    var strAlternate: String? = null,
    var strTeamBadge: String? = null,
    var intFormedYear: Int? = null,
    var strStadium: String? = null,
    var strStadiumThumb: String? = null,
    var strStadiumLocation: String? = null,
    var intStadiumCapacity: Int? = null,
    var strDescriptionEN: String? = null,
    var strManager: String? = null,
    var strWebsite: String? = null,
    var strFacebook: String? = null,
    var strTwitter: String? = null,
    var strInstagram: String? = null,
    var strCountry: String? = null,
    var strTeamBanner: String? = null,
    var strYoutube: String? = null
) {

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_ALTERNATE: String = "TEAM_ALTERNATE"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_FORMED_YEAR: String = "TEAM_FORMED_YEAR"
        const val TEAM_STADIUM: String = "TEAM_STADIUM"
        const val TEAM_STADIUM_THUMB: String = "TEAM_STADIUM_THUMB"
        const val TEAM_STADIUM_LOCATION: String = "TEAM_STADIUM_LOCATION"
        const val TEAM_STADIUM_CAPACITY: String = "TEAM_STADIUM_CAPACITY"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
        const val TEAM_MANAGER: String = "TEAM_MANAGER"
        const val TEAM_WEBSITE: String = "TEAM_WEBSITE"
        const val TEAM_FACEBOOK: String = "TEAM_FACEBOOK"
        const val TEAM_TWITTER: String = "TEAM_TWITTER"
        const val TEAM_INSTAGRAM: String = "TEAM_INSTAGRAM"
        const val TEAM_COUNTRY: String = "TEAM_COUNTRY"
        const val TEAM_BANNER: String = "TEAM_BANNER"
        const val TEAM_YOUTUBE: String = "TEAM_YOUTUBE"
    }

}