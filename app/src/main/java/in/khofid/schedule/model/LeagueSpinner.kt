package `in`.khofid.schedule.model

data class LeagueSpinner(
    val league: String?,
    val idLeague: Int?
){
    override fun toString() = league!!
}