package ru.hedgdifuse.lookapp.shared.base.impl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent
import ru.hedgdifuse.lookapp.shared.base.BasePresenterI
import ru.hedgdifuse.lookapp.shared.base.BaseViewI
import ru.hedgdifuse.lookapp.shared.base.lifecycle.LifecycleState

/**
 * [BasePresenter] - implementation of [BasePresenterI]
 */
abstract class BasePresenter<V : BaseViewI>(
    val view: V
) : BasePresenterI<V>, KoinComponent {

    override var lifecycleState = LifecycleState.STARTED

    /**
     * [presenterScope] - scope for launch coroutines inside presenter, stopped if lifecycle is paused on destroyed
     */
    val presenterScope = CoroutineScope(Dispatchers.Main)

    override fun onStart() {
        lifecycleState = LifecycleState.STARTED
    }
    override fun onResume() {
        lifecycleState = LifecycleState.RESUMED
    }
    override fun onPause() {
        lifecycleState = LifecycleState.PAUSED
        presenterScope.cancel()
    }
    override fun onDestroy() {
        lifecycleState = LifecycleState.DESTROYED
        presenterScope.cancel()
    }

    override fun V.saveCall(executable: V.() -> Unit) {
        if (lifecycleState != LifecycleState.DESTROYED ||
            lifecycleState != LifecycleState.PAUSED
        ) {
            executable(view)
        }
    }
}