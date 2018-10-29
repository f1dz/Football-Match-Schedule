package `in`.khofid.schedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    @SerializedName("idPlayer")
    var idPlayer: Int? = null,

    @SerializedName("strNationality")
    var strNationality: String? = null,

    @SerializedName("strPlayer")
    var strPlayer: String? = null,

    @SerializedName("dateBorn")
    var dateBorn: String? = null,

    @SerializedName("strSigning")
    var strSigning: String? = null,

    @SerializedName("strWage")
    var strWage: String? = null,

    @SerializedName("strBirthLocation")
    var strBirthLocation: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null,

    @SerializedName("strPosition")
    var strPosition: String? = null,

    @SerializedName("strFacebook")
    var strFacebook: String? = null,

    @SerializedName("strWebsite")
    var strWebsite: String? = null,

    @SerializedName("strTwitter")
    var strTwitter: String? = null,

    @SerializedName("strInstagram")
    var strInstagram: String? = null,

    @SerializedName("strHeight")
    var strHeight: String? = null,

    @SerializedName("strWeight")
    var strWeight: String? = null,

    @SerializedName("strThumb")
    var strThumb: String? = null,

    @SerializedName("strCutout")
    var strCutout: String? = null,

    @SerializedName("strFanart1")
    var strFanart: String? = null
): Parcelable