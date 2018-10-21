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
    var strTeamBadge: String? = null
) : Parcelable {

    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_ALTERNATE: String = "TEAM_ALTERNATE"
        const val TEAM_BADGE: String = "TEAM_BADGE"
    }

}