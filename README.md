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
