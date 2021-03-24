package ru.hedgdifuse.lookapp.shared.base

import ru.hedgdifuse.lookapp.shared.base.lifecycle.LifecycleState

/**
 * [BasePresenterI] - Presenter, a part of MVP architecture.
 * @see <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">Wikipedia</a>
 */
interface BasePresenterI<V: BaseViewI> {

    var lifecycleState: LifecycleState

    /**
     * Lifecycle methods
     *
     * [onStart] - called when view is ready to listen presenter
     * [onResume] - called when view becomes to unfreeze state
     * [onPause] - called when view becomes to freeze state
     * [onDestroy] - called when view is destroyed
     *
     * @see <a href="https://developer.android.com/guide/components/activities/activity-lifecycle#lc">Android Developers</a>
     */
    fun onStart()
    fun onResume()
    fun onPause()
    fun onDestroy()


    /**
     * [saveCall] - method for [BaseViewI], required for face calling view methods
     */
    fun V.saveCall(executable: V.() -> Unit)
}