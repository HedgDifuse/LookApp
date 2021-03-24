package ru.hedgdifuse.lookapp.androidApp

import android.app.Application
import ru.hedgdifuse.lookapp.shared.di.startMultiplatformKoin

/**
 * [LookApp] - Application class implementation, required for launch Koin.
 * @see <a href="https://developer.android.com/reference/android/app/Application">Android Developers</a>
 * @see <a href="https://insert-koin.io/">Koin</a>
 */
class LookApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startMultiplatformKoin {

        }
    }
}