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

fun String.toLocalTime(): String {
    val timeStr = this
    val df = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    df.timeZone = TimeZone.getTimeZone("UTC")
    val date = df.parse(timeStr)
    df.timeZone = TimeZone.getDefault()
    return df.format(date)
}

fun String.toLocalDate(time: String): String {
    val dt = this + " " + time
    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val format = SimpleDateFormat("EEE, d MMM yyyy")
    df.timeZone = TimeZone.getTimeZone("UTC")
    val date = df.parse(dt)
    format.timeZone = TimeZone.getDefault()
    return format.format(date)
}

fun Int.toStringVoidNull(): String {
    return if(this == null) "-" else this.toString()
}