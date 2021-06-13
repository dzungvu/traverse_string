package com.dzungvu.restring.wrapper

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.dzungvu.restring.interceptor.TraverseLayoutInterceptor

internal class WrapperFactory(
    private val origin: LayoutInflater.Factory,
    private val interceptorTraverse: TraverseLayoutInterceptor<View>
) : LayoutInflater.Factory {

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        Log.d("WrapperFactory", "WrapperFactory onCreateView $name")
        return origin.onCreateView(name, context, attrs)
            ?.also { interceptorTraverse.interceptAttrs(it, attrs) }
    }
}