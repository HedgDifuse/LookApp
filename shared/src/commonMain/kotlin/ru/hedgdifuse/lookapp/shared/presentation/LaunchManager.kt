package ru.hedgdifuse.lookapp.shared.presentation

import com.russhwolf.settings.Settings

class LaunchManager(
    private val settings: Settings
) {

    fun isCodeScreen() = settings.getStringOrNull("sid") == null
}