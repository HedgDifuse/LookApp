plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation(Dependencies.Android.Koin)
    implementation(Dependencies.Android.KtorClient)
    implementation(Dependencies.Android.CoreKTX)
    implementation(Dependencies.Android.AppCompat)
    implementation(Dependencies.Android.ConstraintLayout)
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "ru.hedgdifuse.lookapp.androidApp"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
    }
}