package kr.loner.willog.data.remote.api

import kr.loner.willog.data.remote.model.Pagination
import kr.loner.willog.data.remote.model.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PhotoApi {
    @GET("/photos")
    suspend fun getPhoto(
        @Query("id") id: String
    ): PhotoResponse

    @GET("/search/photos?order_by=latest")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") parPage: Int?,
    ): Pagination<PhotoResponse>

}