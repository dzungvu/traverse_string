package com.dzungvu.restring.wrapper

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.dzungvu.restring.interceptor.TraverseLayoutInterceptor

class WrapperFactory2(
    private val origin: LayoutInflater.Factory2,
    private val interceptorTraverse: TraverseLayoutInterceptor<View>
) : LayoutInflater.Factory2 {
    private val tag = this::class.java.simpleName

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        Log.d(tag, "onCreateView for $name")
        return origin.onCreateView(name, context, attrs)?.also { interceptorTraverse.interceptAttrs(it, attrs) }
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        Log.d(tag, "onCreateView with parent for $name")
        return origin.onCreateView(parent, name, context, attrs)
            ?.also {
                Log.d(tag, "onCreateView with parent success")
                interceptorTraverse.interceptAttrs(it, attrs)
            }
    }

}
