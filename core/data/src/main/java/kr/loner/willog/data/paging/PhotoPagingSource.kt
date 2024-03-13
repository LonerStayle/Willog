package kr.loner.willog.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kr.loner.willog.data.remote.api.PhotoApi
import kr.loner.willog.data.remote.model.PhotoResponse
import javax.inject.Inject

internal data class PhotoPagingSource @Inject constructor(
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
            LoadResult.Page(
                data = apiSearchResult.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalPage >= page) page + 1 else null
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
        const val DEFAULT_PER_PAGE = 10
    }
}
