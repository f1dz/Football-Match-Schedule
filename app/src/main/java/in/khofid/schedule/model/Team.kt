package `in`.khofid.schedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Team(
    @SerializedName("idTeam")
    var idTeam: Int? = null,

    @SerializedName("strTeam")
    var strTeam: String? = null,

    @SerializedName("strAlternate")
    var strAlternate: String? = null,

    @SerializedName("strTeamBadge")
    var strTeamBadge: String? = null,

    @SerializedName("intFormedYear")
    var intFormedYear: Int? = null,

    @SerializedName("strStadium")
    var strStadium: String? = null,

    @SerializedName("strStadiumThumb")
    var strStadiumThumb: String? = null,

    @SerializedName("strStadiumLocation")
    var strStadiumLocation: String? = null,

    @SerializedName("intStadiumCapacity")
    var intStadiumCapacity: Int? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null,

    @SerializedName("strManager")
    var strManager: String? = null,

    @SerializedName("strWebsite")
    var strWebsite: String? = null,

    @SerializedName("strFacebook")
    var strFacebook: String? = null,

    @SerializedName("strTwitter")
    var strTwitter: String? = null,

    @SerializedName("strInstagram")
    var strInstagram: String? = null,

    @SerializedName("strCountry")
    var strCountry: String? = null,

    @SerializedName("strTeamBanner")
    var strTeamBanner: String? = null,

    @SerializedName("strYoutube")
    var strYoutube: String? = null


) : Parcelable {

    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
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