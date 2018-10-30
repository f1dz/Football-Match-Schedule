package `in`.khofid.schedule.utils

import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.model.Team
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

fun Match.dbGetTeam(ctx: Context, teamId: Int) : Team? {
    var teams: List<Team> = listOf()
    try {
        ctx.database.use {
            val result = select(Team.TABLE_TEAM)
                .whereArgs("(${Team.TEAM_ID} = {id})", "id" to teamId)
            teams = result.parseList(classParser())
        }

    } catch (e: SQLiteConstraintException) {
    }
    if(teams.isEmpty())
        return null
    return teams.first()
}

fun Team.getBadge(): String? {
    return this.strTeamBadge
}

fun Team.insertToDb(ctx: Context) {
    try {
        ctx.database.use {
            with(this){
                insert(
                    Team.TABLE_TEAM,
                    Team.TEAM_ID to idTeam,
                    Team.TEAM_NAME to strTeam,
                    Team.TEAM_ALTERNATE to strAlternate,
                    Team.TEAM_BADGE to strTeamBadge,
                    Team.TEAM_FORMED_YEAR to intFormedYear,
                    Team.TEAM_STADIUM to strStadium,
                    Team.TEAM_STADIUM_THUMB to strStadiumThumb,
                    Team.TEAM_STADIUM_LOCATION to strStadiumLocation,
                    Team.TEAM_STADIUM_CAPACITY to intStadiumCapacity,
                    Team.TEAM_DESCRIPTION to strDescriptionEN,
                    Team.TEAM_MANAGER to strManager,
                    Team.TEAM_WEBSITE to strWebsite,
                    Team.TEAM_FACEBOOK to strFacebook,
                    Team.TEAM_TWITTER to strTwitter,
                    Team.TEAM_INSTAGRAM to strInstagram,
                    Team.TEAM_COUNTRY to strCountry,
                    Team.TEAM_BANNER to strTeamBanner,
                    Team.TEAM_YOUTUBE to strYoutube
                )
            }
        }
    } catch (e: SQLiteConstraintException) {
        Log.e("ERROR", e.localizedMessage)
    }
}