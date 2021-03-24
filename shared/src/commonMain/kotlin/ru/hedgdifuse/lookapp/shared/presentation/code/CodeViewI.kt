package ru.hedgdifuse.lookapp.shared.presentation.code

import ru.hedgdifuse.lookapp.shared.base.BaseViewI

interface CodeViewI: BaseViewI {
    fun submitCode(code: Int)
}