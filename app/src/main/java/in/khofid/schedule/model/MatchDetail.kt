package `in`.khofid.schedule.model

import com.google.gson.annotations.SerializedName

data class MatchDetail(
    @SerializedName("dateEvent")
    var dateEvent: String? = null,

    @SerializedName("strTime")
    var strTime: String? = null,

    @SerializedName("idHomeTeam")
    var idHomeTeam: Int? = null,

    @SerializedName("idAwayTeam")
    var idAwayTeam: Int? = null,

    @SerializedName("strHomeTeam")
    var strHomeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null,

    @SerializedName("intHomeScore")
    var intHomeScore: Int? = null,

    @SerializedName("intAwayScore")
    var intAwayScore: Int? = null,

    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String,

    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String,

    @SerializedName("intHomeShots")
    var intHomeShots: Int? = null,

    @SerializedName("intAwayShots")
    var intAwayShots: Int? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String,

    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String,

    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String,

    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String,

    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: String,

    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: String,

    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String,

    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String,

    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String,

    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String

)