package `in`.khofid.schedule.db

data class Favorite(
    val id: Long?,
    val matchId: String?){

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
    }
}