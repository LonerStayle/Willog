package kr.loner.willog.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kr.loner.willog.data.remote.api.PhotoApi
import kr.loner.willog.data.remote.model.PhotoResponse
import javax.inject.Inject

internal data class PhotoSearchPagingSource @Inject constructor(
    private val photoApi: PhotoApi,
    private val query: String
) : PagingSource<Int, PhotoResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponse> {
        val page = params.key ?: 1
        return try {
            val apiSearchResult = photoApi.searchPhotos(
                query = query,
                page = page,
                parPage = DEFAULT_PER_PAGE
            )
            val totalPage = apiSearchResult.totalPages
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (page <= totalPage ) page + 1 else null
            LoadResult.Page(
                data = apiSearchResult.results,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoResponse>): Int {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        } ?: 1
    }

    companion object {
        const val DEFAULT_PER_PAGE = 100
    }
}
