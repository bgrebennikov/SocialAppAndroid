package com.bgrebennikovv.github.inui.alerts

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BaseAlertDialog<T : ViewBinding>(
    private val inflater: LayoutInflater,
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T,
    private val bgColor: Int? = null
) {

    private val windowBackground = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = 12F.dpToPx()
        setColor(bgColor ?: Color.WHITE)
    }

    val view = inflateMethod.invoke(inflater, null, false)

    val builder: AlertDialog = AlertDialog.Builder(inflater.context).apply {
        setView(view.root)
    }.create().apply {
        window?.setBackgroundDrawable(windowBackground)
    }

    fun setCorners(r : Float){
        windowBackground.cornerRadius = r.dpToPx()
    }



    private fun Float.dpToPx() : Float{
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            inflater.context.resources.displayMetrics
        )
    }

}