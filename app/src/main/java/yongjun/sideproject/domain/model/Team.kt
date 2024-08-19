package yongjun.sideproject.domain.model

data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    // 팀 명 약자. 예) MCI, LIV,
    val tla: String,
    // 팀 로고 png url
    val crest: String,
) {
    val koreanName = when (name) {
        // 프리미어리그 팀들
        "Arsenal FC" -> "아스널"
        "Aston Villa FC" -> "아스톤 빌라"
        "Brighton & Hove Albion FC" -> "브라이튼 앤 호브 알비온"
        "Brentford FC" -> "브렌트포드"
        "Chelsea FC" -> "첼시"
        "Crystal Palace FC" -> "크리스탈 팰리스"
        "Everton FC" -> "에버튼"
        "Fulham FC" -> "풀럼"
        "Liverpool FC" -> "리버풀"
        "Manchester City FC" -> "맨체스터 시티"
        "Manchester United FC" -> "맨체스터 유나이티드"
        "Newcastle United FC" -> "뉴캐슬 유나이티드"
        "Nottingham Forest FC" -> "노팅엄 포레스트"
        "Sheffield United FC" -> "셰필드 유나이티드"
        "Tottenham Hotspur FC" -> "토트넘 홋스퍼"
        "West Ham United FC" -> "웨스트햄 유나이티드"
        "Wolverhampton Wanderers FC" -> "울버햄튼 원더러스"
        "AFC Bournemouth" -> "본머스"
        "Leicester City FC" -> "레스터 시티"
        "Southampton FC" -> "사우스햄튼"
        "Ipswich Town FC" -> "입스위치 타운"

        // 분데스리가 팀들
        "FC Bayern München" -> "바이에른 뮌헨"
        "Borussia Dortmund" -> "보루시아 도르트문트"
        "RB Leipzig" -> "라이프치히"
        "Bayer 04 Leverkusen" -> "레버쿠젠"
        "VfL Wolfsburg" -> "볼프스부르크"
        "Eintracht Frankfurt" -> "아인트라흐트 프랑크푸르트"
        "Borussia Mönchengladbach" -> "보루시아 묀헨글라트바흐"
        "Hertha BSC" -> "헤르타 BSC"
        "SC Freiburg" -> "프라이부르크"
        "FC Augsburg" -> "아우크스부르크"
        "VfB Stuttgart" -> "슈투트가르트"
        "1. FSV Mainz 05" -> "마인츠 05"
        "1. FC Union Berlin" -> "우니온 베를린"
        "TSG 1899 Hoffenheim" -> "호펜하임"
        "1. FC Köln" -> "쾰른"
        "FC Schalke 04" -> "샬케 04"
        "1. FC Heidenheim 1846" -> "FC 하이덴하임"
        "VfL Bochum 1848" -> "보훔"

        // 라리가 팀들
        "Real Madrid CF" -> "레알 마드리드"
        "FC Barcelona" -> "바르셀로나"
        "Club Atlético de Madrid" -> "아틀레티코 마드리드"
        "Sevilla FC" -> "세비야"
        "Real Sociedad de Fútbol" -> "레알 소시에다드"
        "Real Betis" -> "레알 베티스"
        "Villarreal CF" -> "비야레알"
        "Athletic Club" -> "아틀레틱 빌바오"
        "Deportivo Alavés" -> "데포르티보 알라베스"
        "Valencia CF" -> "발렌시아"
        "Celta de Vigo" -> "셀타 비고"
        "CA Osasuna" -> "오사수나"
        "RCD Espanyol de Barcelona" -> "에스파뇰"
        "Real Valladolid CF" -> "레알 바야돌리드"
        "Getafe CF" -> "헤타페"
        "UD Almería" -> "알메리아"
        "Rayo Vallecano" -> "라요 바예카노"
        "Girona FC" -> "지로나"
        "RCD Mallorca" -> "마요르카"
        "Elche CF" -> "엘체"
        "Cádiz CF" -> "카디스"

        // 리그앙 팀들
        "Paris Saint-Germain FC" -> "파리 생제르맹"
        "Olympique de Marseille" -> "올림피크 마르세유"
        "Olympique Lyonnais" -> "올림피크 리옹"
        "AS Monaco FC" -> "AS 모나코"
        "LOSC Lille" -> "릴"
        "Stade Rennais FC" -> "스타드 렌"
        "RC Strasbourg Alsace" -> "RC 스트라스부르"
        "OGC Nice" -> "OGC 니스"
        "Montpellier HSC" -> "몽펠리에 HSC"
        "FC Nantes" -> "FC 낭트"
        "FC Girondins de Bordeaux" -> "지롱댕 보르도"
        "Toulouse FC" -> "툴루즈 FC"
        "Stade de Reims" -> "스타드 드 랭스"
        "Angers SCO" -> "앙제 SCO"
        "Stade Brestois 29" -> "스타드 브레스트 29"
        "FC Lorient" -> "로리앙"
        "Clermont Foot" -> "클레르몽 푸트"
        "ES Troyes AC" -> "트루아 AC"
        "AC Ajaccio" -> "아작시오"
        "FC Metz" -> "메스"

        else -> shortName
    }
}
