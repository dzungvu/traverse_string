package com.dzungvu.restring.repositories

import android.util.Log
import com.dzungvu.restring.core.RestringApp
import com.dzungvu.restring.core.resource_holder.StringHolderApi

internal class TraverseStringRepository(private val api: StringHolderApi) :
    TraverseStringRepositoryApi {
    private val tag = "TraverseStringRepository"

    companion object {
        private var instance: TraverseStringRepositoryApi? = null
        fun initialize(stringHolder: StringHolderApi) {
            instance = TraverseStringRepository(stringHolder)
        }

        fun getInstance(): TraverseStringRepositoryApi {
            return instance
                ?: throw IllegalStateException("Call TraverseStringRepository before use")
        }
    }

    private var curLan = RestringApp.getInstance().currentLanguage.value ?: ""

    init {
        RestringApp.getInstance().currentLanguage.observeForever {
            curLan = it
        }
    }

    override fun getString(id: String): String? {
        Log.d(tag, "Rep find string: $id in lan: $curLan")
        return api.get(curLan)?.get(id)
    }
}