package ru.hedgdifuse.lookapp.shared.base

import ru.hedgdifuse.lookapp.shared.base.render.RenderState

/**
 * [BaseViewI] - View, a part of MVP architecture.
 * @see <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">Wikipedia</a>
 */
interface BaseViewI {
    var renderState: RenderState
}