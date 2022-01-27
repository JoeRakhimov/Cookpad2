package com.joerakhimov.cookpad2.util.dimen

import android.content.Context
import android.util.TypedValue

class DimenUtil(var context: Context) {

    fun dpToPx(value: Int): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            context.resources.displayMetrics
        ).toInt()

}