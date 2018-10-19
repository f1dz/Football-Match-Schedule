package `in`.khofid.schedule.utils

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun String.normalize(): String {
    val list: List<String> = this.split(";").map { it.trim() }
    return list.joinToString("\n", postfix = "")
}