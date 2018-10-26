package `in`.khofid.schedule.detail

import `in`.khofid.schedule.R
import `in`.khofid.schedule.model.Team
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity: AppCompatActivity() {

    private lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        team = intent.getParcelableExtra("team")

        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(htab_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showTeam()
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
        Picasso.get().load(team.strStadiumThumb).into(htab_header)
        team_name.text = team.strTeam
        formed_year.text = team.intFormedYear.toString()
        country.text = team.strCountry
    }
}