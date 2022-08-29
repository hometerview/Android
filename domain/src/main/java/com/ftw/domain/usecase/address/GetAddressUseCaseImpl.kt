package com.ftw.domain.usecase.address

class GetAddressUseCaseImpl : GetAddressUseCase {
    override suspend operator fun invoke(query: String): Result<List<String>> {
        return Result.success(
            listOf(
                "서울 마포구 월드컵북로",
                "서울 마포구 월드컵북로 1길 35",
                "서울 마포구 월드컵북로 2길 3",
                "서울 마포구 월드컵북로 4길 6",
                "서울 마포구 월드컵북로 5가길 12",
                "서울 마포구 월드컵북로 5나길 2",
                "서울 마포구 월드컵북로 52길 9"
            )
        )
    }
}
