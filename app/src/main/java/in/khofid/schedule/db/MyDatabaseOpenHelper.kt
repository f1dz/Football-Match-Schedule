package `in`.khofid.schedule.db

import `in`.khofid.schedule.model.Team
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 8) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            FavoriteMatch.TABLE_FAVORITE_MATCH, true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.MATCH_ID to TEXT + UNIQUE,
            FavoriteMatch.MATCH_DATE to TEXT,
            FavoriteMatch.MATCH_TIME to TEXT,
            FavoriteMatch.MATCH_HOME_TEAM_ID to INTEGER,
            FavoriteMatch.MATCH_HOME_TEAM to TEXT,
            FavoriteMatch.MATCH_HOME_SCORE to INTEGER,
            FavoriteMatch.MATCH_HOME_BADGE to TEXT,
            FavoriteMatch.MATCH_AWAY_TEAM_ID to INTEGER,
            FavoriteMatch.MATCH_AWAY_TEAM to TEXT,
            FavoriteMatch.MATCH_AWAY_SCORE to INTEGER,
            FavoriteMatch.MATCH_AWAY_BADGE to TEXT
        )

        db.createTable(
            Team.TABLE_TEAM, true,
            Team.TEAM_ID to INTEGER + UNIQUE,
            Team.TEAM_NAME to TEXT,
            Team.TEAM_ALTERNATE to TEXT,
            Team.TEAM_BADGE to TEXT,
            Team.TEAM_FORMED_YEAR to INTEGER,
            Team.TEAM_STADIUM to TEXT,
            Team.TEAM_STADIUM_THUMB to TEXT,
            Team.TEAM_STADIUM_LOCATION to TEXT,
            Team.TEAM_STADIUM_CAPACITY to INTEGER,
            Team.TEAM_DESCRIPTION to TEXT,
            Team.TEAM_MANAGER to TEXT,
            Team.TEAM_WEBSITE to TEXT,
            Team.TEAM_FACEBOOK to TEXT,
            Team.TEAM_TWITTER to TEXT,
            Team.TEAM_INSTAGRAM to TEXT,
            Team.TEAM_COUNTRY to TEXT,
            Team.TEAM_BANNER to TEXT,
            Team.TEAM_YOUTUBE to TEXT
        )

        db.createTable(
            FavoriteTeam.TABLE_FAVORITE_TEAM, true,
            FavoriteTeam.TEAM_ID to INTEGER + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_ALTERNATE to TEXT,
            FavoriteTeam.TEAM_BADGE to TEXT,
            FavoriteTeam.TEAM_FORMED_YEAR to INTEGER,
            FavoriteTeam.TEAM_STADIUM to TEXT,
            FavoriteTeam.TEAM_STADIUM_THUMB to TEXT,
            FavoriteTeam.TEAM_STADIUM_LOCATION to TEXT,
            FavoriteTeam.TEAM_STADIUM_CAPACITY to INTEGER,
            FavoriteTeam.TEAM_DESCRIPTION to TEXT,
            FavoriteTeam.TEAM_MANAGER to TEXT,
            FavoriteTeam.TEAM_WEBSITE to TEXT,
            FavoriteTeam.TEAM_FACEBOOK to TEXT,
            FavoriteTeam.TEAM_TWITTER to TEXT,
            FavoriteTeam.TEAM_INSTAGRAM to TEXT,
            FavoriteTeam.TEAM_COUNTRY to TEXT,
            FavoriteTeam.TEAM_BANNER to TEXT,
            FavoriteTeam.TEAM_YOUTUBE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
        db.dropTable(Team.TABLE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)