package com.dzungvu.restring.core

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dzungvu.restring.core.resource_holder.StringHolder
import com.dzungvu.restring.exceptions.RestringNotFoundException
import com.dzungvu.restring.repositories.TraverseStringRepository
import java.util.*

class RestringApp private constructor() : RestringAppApi {

    private val curLan: MutableLiveData<String> = MutableLiveData()
    val currentLanguage: LiveData<String>
    get() = curLan

    companion object {
        private var instance: RestringApp? = null
        private var appContext: Context? = null

        @Synchronized
        fun initialize(context: Context) {
            appContext = context
            instance = RestringApp()
            TraverseStringRepository.initialize(StringHolder)
        }

        fun getInstance() =
            if (instance == null) throw RestringNotFoundException("RestringApp have not initialized. Please place a call in Application level") else instance!!
    }

    init {
        curLan.value = getDefaultLanguage()
        loadStringResourceFromInternalStorageToCache()
    }

    private fun loadStringResourceFromInternalStorageToCache() {

    }

    private fun getDefaultLanguage(): String {
        return Locale.getDefault().language
    }

    override fun loadResource(locale: String?): Map<String, Map<String, String>> {
        // todo: Phase 2
        return mapOf()
    }

    override fun saveResource(json: String) {
        // todo: Phase 2
    }

    override fun saveResource(locale: String, resource: Map<String, String>) {
        // todo: Phase 2
    }

    override fun injectData(data: Map<String, Map<String, String>>) {
        StringHolder.inject(data)
    }

    override fun setLanguage(locale: String) {
        curLan.postValue(locale)
    }

    override fun getLanguage(): String = currentLanguage.value ?: getDefaultLanguage()
}