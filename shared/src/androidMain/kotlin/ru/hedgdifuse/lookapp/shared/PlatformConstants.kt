package ru.hedgdifuse.lookapp.shared

import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import org.koin.core.scope.Scope

actual fun Scope.settings(): Settings = AndroidSettings(get())