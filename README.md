# FootballPaper
**프리미어 리그(영국), 분데스리가(독일), 라리가(스페인), 리그앙(파리) 리그 정보를 간편하게 볼 수 있는 앱.**

각 대회별 현재 순위 및 현재 match list와 다음 match list를 제공한다.
match list에서는 match 클릭 시 youtube로 연동되어 경기 요약을 볼 수 있다.

현재 리그 순위는 궁금하지만 매번 포털 사이트에서 검색하기 귀찮거나, 끝난 경기 요약본을 유튜브로 빠르게 보고 싶은 니즈가 있으면 이 앱이 딱일 것이다.

*개인적인 필요에 따라 계속 개발 중인 앱이므로 기능은 추가될 수 있다.*

## 스크린 샷
디자인은 죄송 할 따름이다.

![image](https://github.com/user-attachments/assets/1845caf8-aaa2-4744-926b-5999e6a61c67)
![image](https://github.com/user-attachments/assets/23779baf-ba36-4c9e-96f7-0cc05f180e3f)
![image](https://github.com/user-attachments/assets/41c9101d-8275-4db2-839a-72268d8a8796)


## 데이터 출처
[football-data.org](https://www.football-data.org/) 에서 실시간 데이터를 받아온다.
무료 플랜을 사용 중이기 때문에 API 호출시 **10 request/min** 제한이 있으나, 실제로는 약간 더 넉넉히 주는 듯하다.

## 앱 실행 방법
#### API KEY 발급
써드 파티 데이터를 가져오기 때문에 API key를 발급 받아야 한다.
https://www.football-data.org/pricing 에서 무료 플랜 클릭 후 간단한 회원 가입을 마치면 인증 메일이 날라오고, 인증하면 API-Key가 제공된다.
물론 여유있으면 유료 플랜해도 되지만, 자주 들여다 볼 앱은 아니라 무료 플랜을 추천한다. 

#### API KEY 추가
API KEY를 발급 받았으면 프로젝트 local.properties 파일에 아래와 같이 API-KEY를 추가한다.

`FOOTBALL_API_KEY = "your-api-key"`

이제 gradle에서 해당 API-KEY를 읽어오기 때문에 빌드에 성공 후 잘 실행될 것이다(아마도?).

참고) debug 모드로 실행하면 리스트 스크롤이 버벅거릴 수도 있다.
recomposition이 필요 이상으로 발생하지도 않는데 왜그런지 잘 모르겠다🤷.
아마도 debug 모드 시 compose 내부 구현 때문이리라 위로해본다.
앱에 설치하여 사용할 땐 gradle에서 isDebuggable = false를 추가해주어 부드럽게 사용하자😊.

```kotlin
buildTypes {
    release {
        isMinifyEnabled = false
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro",
        )
    }
    debug {
        isDebuggable = false
    }
}
```

## 구조 및 기술
구조는 간단히 Data, Domain, Ui 레이어로 나뉘어져있다.
철처히 기능적 필요에 의해 급하게 만든 프로젝트인 만큼 완벽한 아키텍쳐는 당당히 지향하지 않는다.
색상 또한 개판인데, MaterialTheme을 사용하지만 하드코딩된 색이 더 많다. 매우 귀찮은 영역이므로 이는 추후 보완예정이다.

혹시라도 코드를 보다가 이상한 구석이 있다면 개발자가 많이 귀찮았구나 생각하시면 되겠다.


대략적인 스킬 셋은 아래와 같다.
* UI : [Compose](https://developer.android.com/compose), [Circuit](https://slackhq.github.io/circuit/) 
* 의존성 주입 : [Koin](https://insert-koin.io/)
* 네트워크 : [Ktor](https://ktor.io/docs/client-create-new-application.html)

