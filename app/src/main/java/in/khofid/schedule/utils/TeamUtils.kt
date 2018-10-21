package `in`.khofid.schedule.utils

import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.model.Team
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

fun Match.getHomeTeamDetail(ctx: Context): String {
    val match = this
    var teams: List<Team> = listOf()
    try {
        ctx.database.use {
            val result = select(Team.TABLE_TEAM)
                .whereArgs("(TEAM_ID = {id})", "id" to match.homeTeamId!!)
            teams = result.parseList(classParser())
        }

    } catch (e: SQLiteConstraintException) {

    }

    if(teams.isEmpty())
        return ""
    return teams.first().strTeamBadge!!
}

fun Match.getAwayTeamDetail(ctx: Context): String {
    val match = this
    var teams: List<Team> = listOf()
    try {
        ctx.database.use {
            val result = select(Team.TABLE_TEAM)
                .whereArgs("(TEAM_ID = {id})", "id" to match.awayTeamId!!)
            teams = result.parseList(classParser())
        }

    } catch (e: SQLiteConstraintException) {

    }

    if(teams.isEmpty())
        return ""
    return teams.first().strTeamBadge!!
}