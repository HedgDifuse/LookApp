package ru.hedgdifuse.lookapp.shared.base.render

import ru.hedgdifuse.lookapp.shared.base.BaseViewI

/**
 * [RenderState] - state for [BaseViewI]
 */
enum class RenderState {
    LOADING,
    LOADED,
    ERROR
}