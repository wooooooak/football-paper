package yongjun.sideproject.domain.model

data class Season(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchday: Int,
    val winner: String?,
    val stages: List<String>?,
)
