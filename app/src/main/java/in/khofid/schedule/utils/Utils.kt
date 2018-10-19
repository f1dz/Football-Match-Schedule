package `in`.khofid.schedule.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun String.normalize(): String {
    val list: List<String> = this.split(";").map { it.trim() }
    return list.joinToString("\n", postfix = "").trim()
}

fun String.toSimpleDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val format = SimpleDateFormat("EEE, d MMM yyyy")
    val date: Date = dateFormat.parse(this)
    return format.format(date)
}