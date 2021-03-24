package ru.hedgdifuse.lookapp.androidApp.tool

import android.app.Activity
import android.view.LayoutInflater

/**
 * [viewBinding] - delegate for ViewBinding
 * @param bindingInflater - pointer for "inflate" function. Example: ActivityMainBinding::inflate
 */
inline fun <T> Activity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy { bindingInflater.invoke(LayoutInflater.from(this)) }