package `in`.khofid.schedule.match

import `in`.khofid.schedule.R
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class TabAdapter(val ctx: Context, fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> NextMatchFragment()
            1 -> PrevMatchFragment()
            else -> PrevMatchFragment()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> ctx.getString(R.string.next_match_title)
            1 -> ctx.getString(R.string.prev_match_title)
            else -> null
        }
    }

}