package com.dzungvu.restring.wrapper

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import com.dzungvu.restring.apis.TraverseRepositoryApi
import com.dzungvu.restring.interfaces.ITraverseRepository
import com.dzungvu.restring.layout_inflaters.TraverseStringLayoutInflater
import com.dzungvu.restring.repositories.TraverseStringRepository
import com.dzungvu.restring.repositories.TraverseStringRepositoryApi

class TraverseStringContextWrapper(
    private val base: Context,
) : ContextWrapper(base) {
    private val resources by lazy { TraverseStringResource(super.getResources())}
    override fun getResources(): Resources = resources

    override fun getSystemService(name: String): Any? {
        if (Context.LAYOUT_INFLATER_SERVICE == name) {
            return TraverseStringLayoutInflater( this, LayoutInflater.from(base))
        }
        return super.getSystemService(name)
    }

    private class TraverseStringResource(
        base: Resources,
    ) : Resources(base.assets, base.displayMetrics, base.configuration) {
        var repository: TraverseStringRepositoryApi? = TraverseStringRepository.getInstance()

        override fun getText(id: Int): CharSequence {
            return repository?.getString(getResourceEntryName(id)) ?: super.getText(id)
        }
    }
}