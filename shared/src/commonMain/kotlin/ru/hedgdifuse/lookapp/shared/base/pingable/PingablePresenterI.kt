package ru.hedgdifuse.lookapp.shared.base.pingable

import ru.hedgdifuse.lookapp.shared.base.BasePresenterI
import ru.hedgdifuse.lookapp.shared.base.BaseViewI

/**
 * [PingablePresenterI] - interface for [PingablePresenter]
 */
interface PingablePresenterI<V: BaseViewI>: BasePresenterI<V> {

    fun refresh()
}