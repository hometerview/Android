package com.ftw.domain.entitiy

data class SearchResult(
    val company: String,
    val address: String,
    val destination: String
)

val DEMO_RESULTS: List<SearchResult> =
    listOf(
        SearchResult("삼성전자", "서울시 마포구 흥정로 32, 34", "동탄역"),
        SearchResult("삼성생명", "서울시 관악구 무슨로 34, 34", "신림역"),
        SearchResult("르노삼성", "서울시 석규구 긍정로 32, 34", "석규역")
    )
