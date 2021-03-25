package ru.hedgdifuse.lookapp.shared.base.pingable

import org.koin.core.component.inject
import ru.hedgdifuse.lookapp.shared.base.BaseViewI
import ru.hedgdifuse.lookapp.shared.base.impl.BasePresenter
import ru.hedgdifuse.lookapp.shared.usecase.impl.PingUseCase

/**
 * [PingablePresenter] - implementation of [BasePresenter], required for check the internet connection
 */
abstract class PingablePresenter<V: BaseViewI>(view: V): BasePresenter<V>(view), PingablePresenterI<V> {

    private val pingUseCase: PingUseCase by inject()

    suspend fun isServerAvailable() = pingUseCase.get().result

    override fun refresh() = onStart()
}