package ru.skillbranch.devintensive.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import ru.skillbranch.devintensive.R

fun Context.convertDpToPx(dp: Float): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

fun Context.convertSpToPx(sp: Float): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)

fun Context.getColorAccent(): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(R.attr.colorAccent, typedValue, true)
    return typedValue.data
}

fun Context.resolveColor(colorInt: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(colorInt, typedValue, true)
    return  typedValue.data
}