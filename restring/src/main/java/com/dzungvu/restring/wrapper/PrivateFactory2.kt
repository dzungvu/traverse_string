package com.dzungvu.restring.wrapper

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.dzungvu.restring.utils.check_os.atLeastQ
import com.dzungvu.restring.utils.reflection.getField

internal class PrivateFactory2(
    private val inflater: LayoutInflater,
    private val origin: LayoutInflater.Factory2
) : LayoutInflater.Factory2 {
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? =
        origin.onCreateView(name, context, attrs) ?: createCustomView(name, context, attrs)

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? =
        origin.onCreateView(name, context, attrs) ?: createCustomView(name, context, attrs)

    private fun createCustomView(name: String, context: Context, attrs: AttributeSet): View? {
        return name.takeIf { '.' in it }?.let { customViewName ->
            if (atLeastQ()) {
                inflater.createView(context, customViewName, null, attrs)
            } else {
                createCustomViewViaReflection(name, context, attrs)
            }
        }
    }

    private fun createCustomViewViaReflection(
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        return inflater.getField<Array<Any>> { LayoutInflater::class.java.getField("mConstructorArgs") }?.value?.let { constructionArgs ->
            val lastCtx = constructionArgs[0]
            constructionArgs[0] = context
            val result = runCatching { inflater.createView(name, null, attrs) }
            constructionArgs[0] = lastCtx

            result.getOrNull()
        }
    }
}