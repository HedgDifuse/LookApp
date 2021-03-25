package ru.hedgdifuse.lookapp.shared.presentation.profile

import ru.hedgdifuse.lookapp.shared.base.BaseViewI

/**
 * [ProfileViewI] - interface for view implementation
 */
interface ProfileViewI: BaseViewI {
    fun submitTitle(title: String)
    fun goToMainView()
}