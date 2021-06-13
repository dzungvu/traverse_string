package com.dzungvu.restring.interfaces

import com.dzungvu.restring.models.Language

interface LangsApi {
    suspend fun getLanguages(): List<Language>

    suspend fun getTranslations(locale: String): Any
}