package com.ftw.domain.usecase.address

interface GetAddressUseCase {
    suspend operator fun invoke(query: String): Result<List<String>>
}
