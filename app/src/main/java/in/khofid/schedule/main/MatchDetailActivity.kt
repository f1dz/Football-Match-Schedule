package `in`.khofid.schedule.main

import `in`.khofid.schedule.R
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.model.MatchDetail
import `in`.khofid.schedule.utils.invisible
import `in`.khofid.schedule.utils.normalize
import `in`.khofid.schedule.utils.visible
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.match_detail_layout.*
import org.jetbrains.anko.toast

class MatchDetailActivity : AppCompatActivity(), MatchView {

    private lateinit var presenter: MatchPresenter
//    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        setSupportActionBar(toolbar)

        val match: Match = intent.getParcelableExtra("match")

        presenter = MatchPresenter(this)
        presenter.getMatchDetail(match.matchId!!)

    }

    override fun showLoading() {
        progressbar.visible()
    }

    override fun hideLoading() {
        progressbar.invisible()
    }

    override fun showMatch(matches: List<MatchDetail>) {
        val match = matches.first()
        match_date.text = match.dateEvent
        home_team.text = match.strHomeTeam
        away_team.text = match.strAwayTeam
        home_score.text = match.intHomeScore.toString()
        away_score.text = match.intAwayScore.toString()
        home_goal_details.text = match.strHomeGoalDetails.normalize()
        away_goal_details.text = match.strAwayGoalDetails.normalize()
        home_shots.text = match.intHomeShots.toString()
        away_shots.text = match.intAwayShots.toString()
        home_gk.text = match.strHomeLineupGoalkeeper.normalize()
        away_gk.text = match.strAwayLineupGoalkeeper.normalize()
        home_defense.text = match.strHomeLineupDefense.normalize()
        away_defense.text = match.strAwayLineupDefense.normalize()
        home_midfield.text = match.strHomeLineupMidfield.normalize()
        away_midfield.text = match.strAwayLineupMidfield.normalize()
        home_forward.text = match.strHomeLineupForward.normalize()
        away_forward.text = match.strAwayLineupForward.normalize()
        home_substitutes.text = match.strHomeLineupSubstitutes.normalize()
        away_substitutes.text = match.strAwayLineupSubstitutes.normalize()
    }

}
