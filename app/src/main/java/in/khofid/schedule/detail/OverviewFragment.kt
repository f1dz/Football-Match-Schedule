package `in`.khofid.schedule.detail

import `in`.khofid.schedule.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.team_overview_layout.view.*

class OverviewFragment: Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.team_overview_layout, container, false)

        val activity = getActivity() as TeamDetailActivity

        rootView.overview.text = activity.getActivityTeam().strDescriptionEN

        return rootView
    }
}