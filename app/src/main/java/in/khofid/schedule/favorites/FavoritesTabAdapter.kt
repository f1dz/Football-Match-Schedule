package `in`.khofid.schedule.favorites

import `in`.khofid.schedule.R
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class FavoritesTabAdapter(val ctx: Context, fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FavoritesMatchFragment()
            1 -> FavoritesTeamFragment()
            else -> FavoritesMatchFragment()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> ctx.getString(R.string.matches)
            1 -> ctx.getString(R.string.teams)
            else -> null
        }
    }
}