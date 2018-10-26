package `in`.khofid.schedule.db

import `in`.khofid.schedule.model.Team
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 7) {

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
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.MATCH_ID to TEXT + UNIQUE,
            Favorite.MATCH_DATE to TEXT,
            Favorite.MATCH_TIME to TEXT,
            Favorite.MATCH_HOME_TEAM_ID to INTEGER,
            Favorite.MATCH_HOME_TEAM to TEXT,
            Favorite.MATCH_HOME_SCORE to INTEGER,
            Favorite.MATCH_HOME_BADGE to TEXT,
            Favorite.MATCH_AWAY_TEAM_ID to INTEGER,
            Favorite.MATCH_AWAY_TEAM to TEXT,
            Favorite.MATCH_AWAY_SCORE to INTEGER,
            Favorite.MATCH_AWAY_BADGE to TEXT
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
    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(Team.TABLE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)