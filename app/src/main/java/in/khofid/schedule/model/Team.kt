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
): Parcelable