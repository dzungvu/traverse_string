package com.dzungvu.restring.interceptor

import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView

interface TraverseLayoutInterceptor<T : View> {
    fun interceptAttrs(view: T, attrs: AttributeSet)
}

inline fun <reified T : View> interceptor(
    crossinline block: (view: T, attrs: AttributeSet) -> Unit
): Pair<Class<T>, TraverseLayoutInterceptor<T>> =
    T::class.java to object : TraverseLayoutInterceptor<T> {
        override fun interceptAttrs(view: T, attrs: AttributeSet) = block(view, attrs)
    }

fun toolbarInterceptor() = interceptor<Toolbar> { view, attrs ->
    for (index in 0 until attrs.attributeCount) {
        attrs.getAttributeName(index)?.run {
            Log.d("textViewInterceptor", "Index: $index: $this")
            when (this) {
                "title" -> {
                    attrs.getAttributeResourceValue(index, -1).takeIf { it > -1 }?.run {
                        view.title = view.context.getString(this)
                    }
                }

                else -> {
                    // Do nothing
                }
            }
        }
    }
}

fun textViewInterceptor() = interceptor<TextView> { view, attrs ->
    for (index in 0 until attrs.attributeCount) {
        attrs.getAttributeName(index)?.run {
            Log.d("textViewInterceptor", "Index: $index: $this")
            when (this) {
                "text" -> {
                    attrs.getAttributeResourceValue(index, -1).takeIf { it > -1 }?.run {
                        view.text = view.context.getString(this)
                    }
                }

                "hint" -> {
                    attrs.getAttributeResourceValue(index, -1).takeIf { it > -1 }?.run {
                        view.hint = view.context.getString(this)
                    }
                }
                else -> {
                    // Do nothing
                }
            }
        }
    }
}

fun appCompatTextViewInterceptor() = interceptor<AppCompatTextView> { view, attrs ->
    for (index in 0 until attrs.attributeCount) {
        attrs.getAttributeName(index)?.run {
            Log.d("textViewInterceptor", "Index: $index: $this")
            when (this) {
                "text" -> {
                    attrs.getAttributeResourceValue(index, -1).takeIf { it > -1 }?.run {
                        view.text = view.context.getString(this)
                    }
                }

                "hint" -> {
                    attrs.getAttributeResourceValue(index, -1).takeIf { it > -1 }?.run {
                        view.hint = view.context.getString(this)
                    }
                }
                else -> {
                    // Do nothing
                }
            }
        }
    }
}

fun appCompatButtonInterceptor() = interceptor<AppCompatButton> { view, attrs ->
    for (index in 0 until attrs.attributeCount) {
        attrs.getAttributeName(index)?.run {
            Log.d("textViewInterceptor", "Index: $index: $this")
            when (this) {
                "text" -> {
                    attrs.getAttributeResourceValue(index, -1).takeIf { it > -1 }?.run {
                        view.text = view.context.getString(this)
                    }
                }
                else -> {
                    // Do nothing
                }
            }
        }
    }
}