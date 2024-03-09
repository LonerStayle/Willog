package kr.loner.willog.data.mapper

import kr.loner.willog.data.remote.model.UserResponse
import kr.loner.willog.model.User

internal fun UserResponse.toModel() = User(
    id = id,
    username = username
)