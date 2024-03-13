package kr.loner.feature.photo

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kr.loner.core.testing.MainDispatcherRule
import kr.loner.feature.photo.bookmark.PhotoBookmarkUiState
import kr.loner.feature.photo.detail.PhotoDetailUiState
import kr.loner.feature.photo.detail.PhotoDetailViewModel
import kr.loner.willog.domain.ToggleBookmarkUseCase
import kr.loner.willog.domain.GetPhotoUseCase
import kr.loner.willog.model.Photo
import kr.loner.willog.model.User
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class PhotoDetailViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val toggleBookmarkUseCase: ToggleBookmarkUseCase = mockk()
    private val getPhotoUseCase: GetPhotoUseCase = mockk()

    private lateinit var viewModel: PhotoDetailViewModel

    private val fakePhoto = Photo(
        id = "Dwu85P9SOIk",
        width = 0,
        height = 0,
        createdAt = "",
        urls = Photo.Urls("","https://images.unsplash.com/photo-1417325384643-aac51acc9e5d?q=75&fm=jpg&w=200&fit=max"),
        user = User("",""),
        isBookmark = false
    )


    @Test
    fun `사진 조회를 할 수 있다`() = runTest {
        //given
        coEvery { getPhotoUseCase(fakePhoto.id) } returns fakePhoto
        viewModel = PhotoDetailViewModel(getPhotoUseCase, toggleBookmarkUseCase)

        //when
        viewModel.fetchPhoto(fakePhoto.id)

        //then
        viewModel.photoDetailUiState.test{
            val autual = (awaitItem() as PhotoDetailUiState.Success).photo
            assertNotNull(autual)
        }
    }



}