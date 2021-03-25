package ru.hedgdifuse.lookapp.shared.presentation.code

import ru.hedgdifuse.lookapp.shared.base.pingable.PingablePresenterI
import ru.hedgdifuse.lookapp.shared.presentation.code.impl.CodePresenter

/**
 * [CodePresenterI] - interface for [CodePresenter].
 * Is empty, i now, but is required for Strategy pattern.
 * @see <a href="https://en.wikipedia.org/wiki/Strategy_pattern">Wikipedia</a>
 */
interface CodePresenterI: PingablePresenterI<CodeViewI>