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
    var strHomeGoalDetails: String? = null,

    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String? = null,

    @SerializedName("intHomeShots")
    var intHomeShots: Int? = null,

    @SerializedName("intAwayShots")
    var intAwayShots: Int? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String? = null,

    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String? = null,

    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String? = null

)