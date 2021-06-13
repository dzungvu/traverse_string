package com.dzungvu.restring.interceptor

import android.util.AttributeSet
import android.util.Log
import android.view.View

class TraverseLayoutInterceptorCompositor(
    private val interceptors: Map<Class<out View>, TraverseLayoutInterceptor<out View>>
) : TraverseLayoutInterceptor<View> {
    override fun interceptAttrs(view: View, attrs: AttributeSet) {
        (peekInterceptor(view) as? TraverseLayoutInterceptor<View>)?.run {
            Log.d("InterceptorCompositor", "Get view interceptor successfully")
            interceptAttrs(view, attrs)
        } ?: Log.d("InterceptorCompositor", "Get view interceptor failure")
    }

    private fun peekInterceptor(view: View) =
        interceptors[view::class.java] ?: searchAssignableInterceptor(view)

    private fun searchAssignableInterceptor(view: View): TraverseLayoutInterceptor<out View>? {
        interceptors.forEach { (clz, interceptor) ->
            if (clz.isAssignableFrom(view::class.java)) {
                return interceptor
            }
        }

        return null
    }
}