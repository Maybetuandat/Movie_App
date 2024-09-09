package com.example.movie_app.paging

interface Pagination<Key, Item> {

    suspend fun loadNextPage()

    fun reset()
}