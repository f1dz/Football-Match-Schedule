package `in`.khofid.schedule.utils

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun String.normalize(): String {

//    val texts = this.split(";")
//    var normal = arrayListOf<String>()
//    for (text in texts){
//        normal.add(text.trim())
//    }

    val list: List<String> = this.split(";").map { it.trim() }

    return list.joinToString("\n", postfix = "")

//    val rx = Regex("[;]")
//    return rx.replace(this, "\n").trim()
}