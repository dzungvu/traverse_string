package com.dzungvu.restring.interfaces

interface ITraverseRepository {
    fun restore()
    fun getRestring(key: String): String?
    suspend fun fetchData()
}