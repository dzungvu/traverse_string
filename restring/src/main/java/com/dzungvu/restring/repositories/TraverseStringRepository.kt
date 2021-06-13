package com.dzungvu.restring.repositories

import com.dzungvu.restring.core.RestringApp
import com.dzungvu.restring.core.resource_holder.StringHolderApi

internal class TraverseStringRepository(private val api: StringHolderApi) :
    TraverseStringRepositoryApi {

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

    private var curLan = ""

    init {
        RestringApp.getInstance().currentLanguage.observeForever {
            curLan = it
        }
    }

    override fun getString(id: String): String? {
        return api.get(curLan)?.get(id)
    }
}