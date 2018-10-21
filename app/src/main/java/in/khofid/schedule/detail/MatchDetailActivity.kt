package `in`.khofid.schedule.detail

import `in`.khofid.schedule.R
import `in`.khofid.schedule.R.drawable.ic_add_to_favorites
import `in`.khofid.schedule.R.drawable.ic_added_to_favorites
import `in`.khofid.schedule.db.Favorite
import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.model.MatchDetail
import `in`.khofid.schedule.model.Team
import `in`.khofid.schedule.utils.invisible
import `in`.khofid.schedule.utils.normalize
import `in`.khofid.schedule.utils.toSimpleDate
import `in`.khofid.schedule.utils.visible
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
                if(isFavorite) removeFromFavorite() else addToFavorite()

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
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.MATCH_ID to match.matchId,
                    Favorite.MATCH_DATE to match.matchDate,
                    Favorite.MATCH_TIME to match.matchTime,
                    Favorite.MATCH_HOME_TEAM_ID to match.homeTeamId,
                    Favorite.MATCH_HOME_TEAM to match.homeTeam,
                    Favorite.MATCH_HOME_SCORE to match.homeScore,
                    Favorite.MATCH_HOME_BADGE to homeBadge,
                    Favorite.MATCH_AWAY_TEAM_ID to match.awayTeamId,
                    Favorite.MATCH_AWAY_TEAM to match.awayTeam,
                    Favorite.MATCH_AWAY_SCORE to match.awayScore,
                    Favorite.MATCH_AWAY_BADGE to awayBadge
                )
            }
            snackbar(scrollView, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(scrollView, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE,
                    "(MATCH_ID = {id})",
                    "id" to id)
            }
            snackbar(scrollView, "Removed from favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(scrollView, e.localizedMessage).show()
        }
    }

    private fun setFavorite(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(MATCH_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
