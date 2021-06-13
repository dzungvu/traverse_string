package com.dzungvu.restring.apis

import com.dzungvu.restring.interfaces.LangsApi
import com.dzungvu.restring.interfaces.ITraverseRepository
import java.util.*
import kotlin.collections.HashMap

class TraverseRepositoryApi(
//    private val api: LangsApi,
//    private val localeManager: LocaleProvider,
//    private val cache: TranslationsCache
) : ITraverseRepository {
    @Volatile
    private var visited: Boolean = false

    @Volatile
    private var langMap: Map<Locale, Map<String, String>> = emptyMap()

    override fun restore() {
//        langMap = cache.getTranslations()
    }

    override suspend fun fetchData() {
//        val langs = api.runCall { getLanguages() }
//
//        val newLangMap = HashMap<Locale, Map<String, String>>()
//        langs?.forEach {
//            api.runCall { getTranslations(it.locale) }?.let { translations ->
//                newLangMap[Locale(it.locale)] = mapToLangMap(translations)
//            } ?: return
//        }
//
//        cache.saveTranslations(newLangMap)
//
//        if (!visited) {
//            langMap = newLangMap
//        }
    }

    override fun getRestring(key: String): String? {
//        visited = true
//        return langMap[localeManager.currentLocale]?.get(key)
        return "string here"
    }

    private inline fun <R> LangsApi.runCall(apiCall: LangsApi.() -> R): R? =
        runCatching { apiCall() }.onFailure {}.getOrNull()

    private fun mapToLangMap(translations: Any): Map<String, String> {
        val result = HashMap<String, String>()
        if (translations is Map<*, *>) {
            val data = translations["data"]
            if (data is Map<*, *>) {
                traverse("", data, result)
            }
        }
        return result
    }

    private fun traverse(key: String, value: Map<*, *>, output: MutableMap<String, String>) {
        value.forEach { (subKey, subValue) ->
            val newKey = if (key.isNotEmpty()) "${key}_$subKey" else subKey.toString()
            if (subValue is Map<*, *>) {
                traverse(newKey, subValue, output)
            } else if (subValue != null) {
                output[newKey] = convert(subValue.toString()).replace("...", "…")
            }
        }
    }

    private fun convert(value: String): String {
        return value
    }
}