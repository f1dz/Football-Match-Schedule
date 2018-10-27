package `in`.khofid.schedule.utils

import `in`.khofid.schedule.R
import android.content.Context
import android.widget.ArrayAdapter

class Common() {

    companion object {

        fun spinnerAdapter(ctx: Context): ArrayAdapter<Any> {
            val spinnerItems = ctx.resources.getStringArray(R.array.league)
            return ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        }


    }

}