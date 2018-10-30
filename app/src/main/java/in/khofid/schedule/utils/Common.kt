package `in`.khofid.schedule.utils

import `in`.khofid.schedule.R
import `in`.khofid.schedule.model.League
import `in`.khofid.schedule.model.LeagueSpinner
import android.content.Context
import android.widget.ArrayAdapter

class Common() {

    companion object {

        fun spinnerAdapter(ctx: Context, leagues: List<League>): ArrayAdapter<LeagueSpinner> {
            val itemList: MutableList<LeagueSpinner> = mutableListOf()
            leagues.forEach { itemList.add(LeagueSpinner(it.strLeague, it.idLeague)) }

            return ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, itemList)
        }


    }

}