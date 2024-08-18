package yongjun.sideproject.domain.model

data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    // 팀 명 약자. 예) MCI, LIV,
    val tla: String,
    // 팀 로고 png url
    val crest: String,
)
