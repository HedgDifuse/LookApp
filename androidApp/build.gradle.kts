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
    implementation(Dependencies.Android.Material)
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}