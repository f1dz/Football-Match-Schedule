package `in`.khofid.schedule.detail

import `in`.khofid.schedule.R
import `in`.khofid.schedule.R.drawable.ic_add_to_favorites
import `in`.khofid.schedule.R.drawable.ic_added_to_favorites
import `in`.khofid.schedule.db.FavoriteMatch
import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.model.MatchDetail
import `in`.khofid.schedule.model.Team
import `in`.khofid.schedule.utils.*
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.match_detail_layout.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    private lateinit var detailPresenter: MatchDetailPresenter

    private var menuItem: Menu? = null
    private lateinit var match: Match
    private lateinit var id: String
    private var isFavorite: Boolean = false
    private lateinit var homeBadge: String
    private lateinit var awayBadge: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        match = intent.getParcelableExtra("match")
        id = match.matchId.toString()

        detailPresenter = MatchDetailPresenter(this)
        detailPresenter.getMatchDetail(match.matchId!!)

        favoriteState()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressbar.visible()
    }

    override fun hideLoading() {
        progressbar.invisible()
    }

    override fun showMatch(matches: List<MatchDetail>) {
        val match = matches.first()
        match_date.text = match.dateEvent?.toSimpleDate()
        match_time.text = match.strTime?.toLocalTime()
        home_team.text = match.strHomeTeam
        away_team.text = match.strAwayTeam
        home_score.text = match.intHomeScore?.toString()
        away_score.text = match.intAwayScore?.toString()
        home_goal_details.text = match.strHomeGoalDetails?.normalize()
        away_goal_details.text = match.strAwayGoalDetails?.normalize()
        home_shots.text = match.intHomeShots?.toString()
        away_shots.text = match.intAwayShots?.toString()
        home_gk.text = match.strHomeLineupGoalkeeper?.normalize()
        away_gk.text = match.strAwayLineupGoalkeeper?.normalize()
        home_defense.text = match.strHomeLineupDefense?.normalize()
        away_defense.text = match.strAwayLineupDefense?.normalize()
        home_midfield.text = match.strHomeLineupMidfield?.normalize()
        away_midfield.text = match.strAwayLineupMidfield?.normalize()
        home_forward.text = match.strHomeLineupForward?.normalize()
        away_forward.text = match.strAwayLineupForward?.normalize()
        home_substitutes.text = match.strHomeLineupSubstitutes?.normalize()
        away_substitutes.text = match.strAwayLineupSubstitutes?.normalize()

        detailPresenter.getTeamDetail(match)
    }

    override fun showBadge(teams: ArrayList<Team>) {
        homeBadge = teams.first().strTeamBadge!!
        awayBadge = teams.last().strTeamBadge!!
        Picasso.get().load(teams.first().strTeamBadge).into(home_badge)
        Picasso.get().load(teams.last().strTeamBadge).into(away_badge)
    }

    private fun addToFavorite() {

        if (::homeBadge.isInitialized && ::awayBadge.isInitialized) {
            try {
                database.use {
                    insert(
                        FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.MATCH_ID to match.matchId,
                        FavoriteMatch.MATCH_DATE to match.matchDate,
                        FavoriteMatch.MATCH_TIME to match.matchTime,
                        FavoriteMatch.MATCH_HOME_TEAM_ID to match.homeTeamId,
                        FavoriteMatch.MATCH_HOME_TEAM to match.homeTeam,
                        FavoriteMatch.MATCH_HOME_SCORE to match.homeScore,
                        FavoriteMatch.MATCH_HOME_BADGE to homeBadge,
                        FavoriteMatch.MATCH_AWAY_TEAM_ID to match.awayTeamId,
                        FavoriteMatch.MATCH_AWAY_TEAM to match.awayTeam,
                        FavoriteMatch.MATCH_AWAY_SCORE to match.awayScore,
                        FavoriteMatch.MATCH_AWAY_BADGE to awayBadge
                    )
                }
                scrollView.snackbar(R.string.favorite_added).show()
            } catch (e: SQLiteConstraintException) {
                scrollView.snackbar(e.localizedMessage).show()
            }
        } else isFavorite = !isFavorite

    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteMatch.TABLE_FAVORITE_MATCH,
                    "(${FavoriteMatch.MATCH_ID} = {id})",
                    "id" to id
                )
            }
            scrollView.snackbar(R.string.favorite_removed).show()
        } catch (e: SQLiteConstraintException) {
            scrollView.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                .whereArgs(
                    "(${FavoriteMatch.MATCH_ID} = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
