package `in`.khofid.schedule.match

import `in`.khofid.schedule.R
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class TabAdapter(val ctx: Context, fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var bundle = Bundle()
        var mFragment = MatchesFragment()
         when(position) {
            0 -> bundle.putInt("tabIndex", 0)
            1 -> bundle.putInt("tabIndex", 1)
            else -> bundle.putInt("tabIndex", 0)
        }
        mFragment.arguments = bundle
        return mFragment
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