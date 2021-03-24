package ru.hedgdifuse.lookapp.shared.base.lifecycle

/**
 * [LifecycleState] - lifecycle state for [BasePresenterI] and [BaseViewI]
 * @see <a href="https://developer.android.com/guide/components/activities/activity-lifecycle#lc">Android Developers</a>
 */
enum class LifecycleState {
    STARTED,
    RESUMED,
    PAUSED,
    DESTROYED
}