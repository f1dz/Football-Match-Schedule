package `in`.khofid.schedule.db

data class FavoriteMatch(
    val id: Long?,
    val matchId: String?,
    val matchDate: String?,
    val matchTime: String?,
    val matchHomeTeamId: Int?,
    val matchHomeTeam: String?,
    val matchHomeScore: Int?,
    val matchHomeBadge: String?,
    val matchAwayTeamId: Int?,
    val matchAwayTeam: String?,
    val matchAwayScore: Int?,
    val matchAwayBadge: String?){

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val MATCH_TIME: String = "MATCH_TIME"
        const val MATCH_HOME_TEAM_ID: String = "MATCH_HOME_TEAM_ID"
        const val MATCH_HOME_TEAM: String = "MATCH_HOME_TEAM"
        const val MATCH_HOME_SCORE: String = "MATCH_HOME_SCORE"
        const val MATCH_HOME_BADGE: String = "MATCH_HOME_BADGE"
        const val MATCH_AWAY_TEAM_ID: String = "MATCH_AWAY_TEAM_ID"
        const val MATCH_AWAY_TEAM: String = "MATCH_AWAY_TEAM"
        const val MATCH_AWAY_SCORE: String = "MATCH_AWAY_SCORE"
        const val MATCH_AWAY_BADGE: String = "MATCH_AWAY_BADGE"
    }
}