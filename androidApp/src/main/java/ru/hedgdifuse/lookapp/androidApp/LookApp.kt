package ru.hedgdifuse.lookapp.androidApp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.hedgdifuse.lookapp.shared.di.startMultiplatformKoin
import ru.hedgdifuse.lookapp.shared.presentation.code.CodePresenterI
import ru.hedgdifuse.lookapp.shared.presentation.code.CodeViewI
import ru.hedgdifuse.lookapp.shared.presentation.code.impl.CodePresenter
import ru.hedgdifuse.lookapp.shared.presentation.profile.ProfilePresenterI
import ru.hedgdifuse.lookapp.shared.presentation.profile.ProfileViewI
import ru.hedgdifuse.lookapp.shared.presentation.profile.impl.ProfilePresenter

/**
 * [LookApp] - Application class implementation, required for launch Koin.
 * @see <a href="https://developer.android.com/reference/android/app/Application">Android Developers</a>
 * @see <a href="https://insert-koin.io/">Koin</a>
 */
class LookApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startMultiplatformKoin {
            androidContext(this@LookApp)

            modules(
                module { // MainActivity
                    factory { (codeView: CodeViewI) ->
                        CodePresenter(codeView)
                    } bind CodePresenterI::class
                },

                module { // ProfileActivity
                    factory { (profileView: ProfileViewI) ->
                        ProfilePresenter(profileView)
                    } bind ProfilePresenterI::class
                }
            )
        }
    }
}