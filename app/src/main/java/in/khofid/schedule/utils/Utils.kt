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
    val localeId = Locale("id", "ID")
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", localeId)
    val format = SimpleDateFormat("EEE, d MMM yyyy", localeId)
    val date: Date = dateFormat.parse(this)
    return format.format(date)
}

fun String.toLocalTime(): String {
    val timeStr = this
    val localeId = Locale("id", "ID")
    val df = SimpleDateFormat("HH:mm", localeId)
    df.timeZone = TimeZone.getTimeZone("UTC")
    val date = df.parse(timeStr)
    df.timeZone = TimeZone.getDefault()
    return df.format(date)
}

fun String.toLocalDate(time: String?): String {
    val mTime = time ?: "00:00:00+00:00:00"
    val dt = this + " " + mTime
    val localeId = Locale("id", "ID")
    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localeId)
    val format = SimpleDateFormat("EEE, d MMM yyyy", localeId)
    df.timeZone = TimeZone.getTimeZone("UTC")
    val date = df.parse(dt)
    format.timeZone = TimeZone.getDefault()
    return format.format(date)
}

fun String.toLocalDateTime(time: String?): String {
    val mTime = time ?: "00:00:00+00:00:00"
    val dt = this + " " + mTime
    val localeId = Locale("id", "ID")
    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localeId)
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localeId)
    df.timeZone = TimeZone.getTimeZone("UTC")
    val date = df.parse(dt)
    format.timeZone = TimeZone.getDefault()
    return format.format(date)
}

fun String.toMillis(): Long {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date: Date = sdf.parse(this)

    return date.getTime()
}

fun String.isPast(): Boolean {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this).before(Date())
}

fun String.encodeUrl(): String {
    return this.replace(" ", "%20")
}