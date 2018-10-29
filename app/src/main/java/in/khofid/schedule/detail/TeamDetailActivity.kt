package `in`.khofid.schedule.detail

import `in`.khofid.schedule.R
import `in`.khofid.schedule.db.FavoriteTeam
import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.model.Team
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.image

class TeamDetailActivity: AppCompatActivity() {

    private var team: Team = Team()
    private lateinit var tabAdapter: TabTeamDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        team = intent.getParcelableExtra("team")

        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(htab_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(team.strTeam)

        tabAdapter = TabTeamDetailAdapter(this, supportFragmentManager, team)
        viewpager.adapter = tabAdapter
        team_tabs.setupWithViewPager(viewpager)

        fab.setOnClickListener { fabOnClick() }

        showTeam()
        setFavoriteIcon()
    }

    private fun fabOnClick() {
        if(isFavorited()) removeFromFavorite() else addToFavorite()
        setFavoriteIcon()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showTeam(){
        Picasso.get().load(team.strTeamBadge).into(team_badge)
        Picasso.get().load(team.strStadiumThumb).into(header)
        team_name.text = team.strTeam
        formed_year.text = team.intFormedYear.toString()
        stadium.text = team.strStadium
    }

    fun getActivityTeam() = team

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to team.idTeam,
                    FavoriteTeam.TEAM_NAME to team.strTeam,
                    FavoriteTeam.TEAM_ALTERNATE to team.strAlternate,
                    FavoriteTeam.TEAM_BADGE to team.strTeamBadge,
                    FavoriteTeam.TEAM_FORMED_YEAR to team.intFormedYear,
                    FavoriteTeam.TEAM_STADIUM to team.strStadium,
                    FavoriteTeam.TEAM_STADIUM_THUMB to team.strStadiumThumb,
                    FavoriteTeam.TEAM_STADIUM_LOCATION to team.strStadiumLocation,
                    FavoriteTeam.TEAM_STADIUM_CAPACITY to team.intStadiumCapacity,
                    FavoriteTeam.TEAM_DESCRIPTION to team.strDescriptionEN,
                    FavoriteTeam.TEAM_MANAGER to team.strManager,
                    FavoriteTeam.TEAM_WEBSITE to team.strWebsite,
                    FavoriteTeam.TEAM_FACEBOOK to team.strFacebook,
                    FavoriteTeam.TEAM_TWITTER to team.strTwitter,
                    FavoriteTeam.TEAM_INSTAGRAM to team.strInstagram,
                    FavoriteTeam.TEAM_COUNTRY to team.strCountry,
                    FavoriteTeam.TEAM_BANNER to team.strTeamBanner,
                    FavoriteTeam.TEAM_YOUTUBE to team.strYoutube
                )
            }
            viewpager.snackbar(R.string.favorite_added).show()
        } catch (e: SQLiteConstraintException) {
            viewpager.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteTeam.TABLE_FAVORITE_TEAM,
                    "(${FavoriteTeam.TEAM_ID} = {id})",
                    "id" to team.idTeam!!
                )
            }
            viewpager.snackbar(R.string.favorite_removed).show()
        } catch (e: SQLiteConstraintException) {
            viewpager.snackbar(e.localizedMessage)
        }
    }

    private fun isFavorited(): Boolean {
        var favorited = false
        try {
            database.use {
                val result  = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(${FavoriteTeam.TEAM_ID}) = {id}",
                        "id" to team.idTeam!!
                    )
                val favorite = result.parseList(classParser<FavoriteTeam>())
                if(favorite.isNotEmpty()) favorited = true
            }
        } catch (e: SQLiteConstraintException) {
            favorited = false
        }

        return favorited
    }

    private fun setFavoriteIcon() {
        if(isFavorited()) fab.image = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else fab.image = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}