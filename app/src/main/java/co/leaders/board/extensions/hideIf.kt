package co.leaders.board.extensions

import android.view.View


fun View.hideIf(condition: Boolean) {
    if(condition) hide() else show()
}

fun View.showIf(condition: Boolean) {
    if(condition) show() else hide()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}