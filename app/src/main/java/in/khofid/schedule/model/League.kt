package `in`.khofid.schedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League (
    @SerializedName("idLeague")
    var idLeague: Int? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null,

    @SerializedName("strLeagueAlternate")
    var strLeagueAlternate: String? = null
): Parcelable