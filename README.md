## retrofit 환경설정 (libs.versions.tomi)
- retrofit ={group="com.squareup.retrofit2", name="retrofit", version.ref="retrofit"}
- okhttp = { group = "com.squareup.okhttp3", name = "okhttp", version.ref = "okhttp" }
- okhttpConverter = { group = "com.squareup.retrofit2", name = "converter-gson", version.ref = "okhttpConverter" }
------------------------------------------------------------------------------------------------------------------
- retrofit = "2.9.0"
- okhttp = "5.0.0-alpha.12"
- okhttpConverter ="2.9.0"
-----------------------------------------------------------------------------------------------------------------
## retrofit 환경설정 (build.gradle.kts)
- implementation(libs.okhttp)
- implementation(libs.okhttpConverter)
- implementation(libs.retrofit)
--------------------------------------------------------


## 데이터 구조 

- Common 패키지 : 유틸함수
    - Constants :  URL 정보저장
    - Resource  :  데이터 통신시 성공, 로딩, 실패 상태 체크
      
- remote 패키지 : 서버 통신
    - dto -> di -> AppModule : Retrofit 설정
    - RepositoryMoudle -> 네트워크에 전달된 데이터를 각각의 UseCase로 전달
 
- domain 패키지 
    -Repository : usecase에 데이터 전달 
