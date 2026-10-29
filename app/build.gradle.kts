plugins { id("com.android.application") }
android {
 namespace = "jp.ieneko.mannenreki"
 compileSdk = 35
 defaultConfig {
  applicationId = "jp.ieneko.mannenreki"
  minSdk = 26
  targetSdk = 34
  versionCode = 220
  versionName = "2.2.0"
 }
 compileOptions {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
 }
}
