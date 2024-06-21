package br.com.aline.marvel_app.util

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(
        requireContext(),
        message,
        duration
    ).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun String.limitDescription(characters: Int): String {
    if(this.length > characters){
        val firstCharacter = 0
        return "${this.substring(firstCharacter, characters)}..."
    }
    return this
}