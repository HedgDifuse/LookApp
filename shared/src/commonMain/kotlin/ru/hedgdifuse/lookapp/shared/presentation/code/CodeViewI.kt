package ru.hedgdifuse.lookapp.shared.presentation.code

import ru.hedgdifuse.lookapp.shared.base.BaseViewI

/**
 * [CodeViewI] - interface for view implementation
 */
interface CodeViewI: BaseViewI {
    fun submitCode(code: Int)
    fun goToProfileScreen()
}