package kr.loner.feature.photo.search


sealed interface PhotoSearchEffect {
    object Ide:PhotoSearchEffect
    object RefreshPaging : PhotoSearchEffect
}