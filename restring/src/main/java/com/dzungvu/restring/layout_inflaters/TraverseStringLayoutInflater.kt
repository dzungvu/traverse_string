package com.dzungvu.restring.layout_inflaters

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dzungvu.restring.interceptor.*
import com.dzungvu.restring.utils.reflection.getMethod
import com.dzungvu.restring.wrapper.PrivateFactory2
import com.dzungvu.restring.wrapper.WrapperFactory
import com.dzungvu.restring.wrapper.WrapperFactory2
import org.xmlpull.v1.XmlPullParser

class TraverseStringLayoutInflater(
    context: Context,
    parent: LayoutInflater,
    cloned: Boolean = false,
    private val customInterceptors: Map<Class<out View>, TraverseLayoutInterceptor<out View>> = mapOf()
) : LayoutInflater(parent, context) {
    private val tag = "CustomLayoutInflater"
    private var privateFactorySet: Boolean = false
    private val interceptor = TraverseLayoutInterceptorCompositor(hashMapOf(
        textViewInterceptor(),
        toolbarInterceptor(),
        appCompatTextViewInterceptor(),
        appCompatButtonInterceptor(),
    ).also { it.putAll(customInterceptors) })

    init {
        if (!cloned) {
            factory?.let { factory = it }
            factory2?.let { factory2 = it }
        }
    }

    override fun setFactory(factory: Factory?) {
        val final = factory?.let { (it as? WrapperFactory) ?: WrapperFactory(it, interceptor) }
        super.setFactory(final)
    }

    override fun setFactory2(factory: Factory2?) {
        val final = factory?.let { (it as? WrapperFactory2) ?: WrapperFactory2(it, interceptor) }
        super.setFactory2(final)
    }

    override fun cloneInContext(newContext: Context): LayoutInflater {
        return TraverseStringLayoutInflater(newContext, this, true)
    }

    override fun inflate(parser: XmlPullParser, root: ViewGroup?, attachToRoot: Boolean): View {
//        setPrivateFactoryInternal()
        return super.inflate(parser, root, attachToRoot)
    }

    private fun setPrivateFactoryInternal() {
        if (privateFactorySet) return

        val ctx = context
        if (ctx !is Factory2) {
            privateFactorySet = true
            return
        }

        getMethod("setPrivateFactory")?.invoke(
            WrapperFactory2(
                PrivateFactory2(
                    this,
                    ctx
                ), interceptor
            )
        )
        privateFactorySet = true
    }

    override fun onCreateView(
        name: String?,
        attrs: AttributeSet?
    ): View? {
        Log.d(tag, "onCreateView $name")
        for (prefix in androidPrefixes) {
            try {
                val view = createView(name, prefix, attrs)
                if (view != null) return view
            } catch (e: ClassNotFoundException) {
            }
        }

        return super.onCreateView(name, attrs)
    }

    companion object {
        private val androidPrefixes = listOf(
            "android.widget.",
        )
    }
}