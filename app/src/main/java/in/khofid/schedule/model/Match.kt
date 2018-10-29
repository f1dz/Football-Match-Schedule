package `in`.khofid.schedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    @SerializedName("idEvent")
    var matchId: Int? = null,

    @SerializedName("dateEvent")
    var matchDate: String? = null,

    @SerializedName("strTime")
    var matchTime: String? = null,

    @SerializedName("idHomeTeam")
    var homeTeamId: Int?  = null,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("idAwayTeam")
    var awayTeamId: Int? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: Int? = null,

    @SerializedName("intAwayScore")
    var awayScore: Int? = null,

    @SerializedName("strSport")
    var sport: String? = null,

    @SerializedName("strEvent")
    var event: String? = null,

    @SerializedName("strFilename")
    var fileName: String? = null

): Parcelable