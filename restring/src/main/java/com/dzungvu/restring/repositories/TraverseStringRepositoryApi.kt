package com.dzungvu.restring.repositories

interface TraverseStringRepositoryApi {
    /**
     * Get string from StringHolder
     */
    fun getString(id: String): String?
}