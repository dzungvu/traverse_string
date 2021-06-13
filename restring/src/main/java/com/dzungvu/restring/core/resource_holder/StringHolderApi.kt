package com.dzungvu.restring.core.resource_holder

internal interface StringHolderApi {
    fun inject(data: Map<String, Map<String, String>>)

    fun get(locale: String): Map<String, String>?

    fun get(): Map<String, Map<String, String>>
}