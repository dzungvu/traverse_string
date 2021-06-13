package com.dzungvu.restring.core.resource_holder

internal object StringHolder : StringHolderApi {
    private var stringData: MutableMap<String, Map<String, String>> = mutableMapOf()

    override fun inject(data: Map<String, Map<String, String>>) {
        stringData.clear()
        stringData.putAll(data)
    }

    override fun get(): Map<String, Map<String, String>> = stringData

    override fun get(locale: String): Map<String, String>? {
        return try {
            stringData[locale]
        } catch (ex: Exception) {
            null
        }
    }
}