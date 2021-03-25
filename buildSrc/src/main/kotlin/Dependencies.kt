import Versions.Android.app_compat_version
import Versions.Android.core_ktx_version
import Versions.Android.constraint_layout_version
import Versions.Android.material_version

import Versions.koin_version
import Versions.ktor_version
import Versions.settings_version
import Versions.kotlinx_serializer_version
import Versions.coroutines_version

object Dependencies {

    object Common {
        // Koin
        const val Koin = "io.insert-koin:koin-core:$koin_version"

        // Ktor
        const val Ktor = "io.ktor:ktor-client-core:$ktor_version"
        const val KtorSerialization = "io.ktor:ktor-client-serialization:$ktor_version"

        // KotlinX
        const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
        const val KotlinXSerializer = "org.jetbrains.kotlinx:kotlinx-serialization-cbor:$kotlinx_serializer_version"

        // Settings
        const val MultiplatformSettings = "com.russhwolf:multiplatform-settings-no-arg:$settings_version"
    }

    object Android {
        // Koin
        const val Koin = "io.insert-koin:koin-android:$koin_version"

        // Ktor
        const val KtorClient = "io.ktor:ktor-client-android:$ktor_version"

        // AndroidX
        const val CoreKTX = "androidx.core:core-ktx:$core_ktx_version"
        const val AppCompat = "androidx.appcompat:appcompat:$app_compat_version"
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:$constraint_layout_version"

        // Material
        const val Material = "com.google.android.material:material:$material_version"
    }

    object Desktop {
        const val KtorClient = "io.ktor:ktor-client-jetty:$ktor_version"
    }
}