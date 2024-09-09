package com.example.movie_app.paging

import android.util.Log
import retrofit2.Response

class PaginationFactory<Key, Item>(
    private val innitialPage: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextPage: Key) -> Response<Item>,
    private inline val getNextKey: suspend (Item) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: Item, newPage: Key) -> Unit,

    ) : Pagination<Key, Item> {
    private var currentKey = innitialPage
    private var isMakingRequest = false

    override suspend fun loadNextPage() {

        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        try {
            val response = onRequest(currentKey)
            if (response.isSuccessful) {
                isMakingRequest = false
                val items = response.body()!!
                currentKey = getNextKey(items)!!
                onSuccess(items, currentKey)
                onLoadUpdated(false)
            }
        } catch (e: Exception) {
            Log.e("loadNextPage: ", e.toString())
            onError(e)
            onLoadUpdated(false)
        }

    }

    override fun reset() {


        currentKey = innitialPage
    }
}