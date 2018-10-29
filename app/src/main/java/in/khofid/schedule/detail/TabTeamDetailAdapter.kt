package `in`.khofid.schedule.detail

import `in`.khofid.schedule.R
import `in`.khofid.schedule.model.Team
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class TabTeamDetailAdapter(val ctx: Context, fm: FragmentManager, val team: Team): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> OverviewFragment()
            1 -> PlayersFragment()
            else -> OverviewFragment()
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> ctx.getString(R.string.overview_title)
            1 -> ctx.getString(R.string.players_title)
            else -> null
        }
    }
}