import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.4.30"
}

configurations {
    create("androidTestApi")
    create("androidTestDebugApi")
    create("androidTestReleaseApi")
    create("testApi")
    create("testDebugApi")
    create("testReleaseApi")
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Common.Koin)
                implementation(Dependencies.Common.Ktor)
                implementation(Dependencies.Common.KtorSerialization)
                implementation(Dependencies.Common.KotlinXSerializer)
                implementation(Dependencies.Common.MultiplatformSettings)
                implementation(Dependencies.Common.Coroutines)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Common.Koin)
            }
        }
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
    }
}