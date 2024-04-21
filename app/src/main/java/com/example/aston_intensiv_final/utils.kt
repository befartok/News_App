package com.example.aston_intensiv_final

import android.graphics.Color
import android.text.AutoText
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.view.View.OnClickListener

fun SpannableString.withClicableSpan(
    clickablePart: String,
     color: Int,
    isUnderLineText: Boolean = true,
    onClickListener: () -> Unit
):SpannableString{
    val clickableSpan = object : ClickableSpan(){
        override fun onClick(widget: View) {
            onClickListener.invoke()
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.color = color
            ds.isUnderlineText = isUnderLineText
        }
    }
    val clickablePartStart = indexOf(clickablePart)
    setSpan(
        clickableSpan,
        clickablePartStart,
        clickablePartStart + clickablePart.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return this

}