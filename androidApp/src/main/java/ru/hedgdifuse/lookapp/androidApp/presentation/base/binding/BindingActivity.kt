package ru.hedgdifuse.lookapp.androidApp.presentation.base.binding

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import ru.hedgdifuse.lookapp.androidApp.R
import ru.hedgdifuse.lookapp.androidApp.tool.viewBinding
import ru.hedgdifuse.lookapp.shared.base.BasePresenterI
import ru.hedgdifuse.lookapp.shared.base.BaseViewI

/**
 * [BindingActivity] - implementation of [BaseViewI]
 * @param defaultTheme - theme, to set by default. Needed for realization SplashScreen feature
 * @see [viewBinding] for information about viewBinding working
 * @see <a href="https://antonioleiva.com/branded-launch-screen/">Antonio Leiva</a>
 */
abstract class BindingActivity<VB : ViewBinding>(
    bindingInflater: (layoutInflater: LayoutInflater) -> VB,
    @StyleRes private val defaultTheme: Int = R.style.AppTheme
): AppCompatActivity(), BaseViewI {

    abstract val presenter: BasePresenterI<*>

    val binding by viewBinding(bindingInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(defaultTheme)
        setContentView(binding.root)

        presenter.onStart()
    }

    override fun onPause() {
        super.onPause()

        presenter.onPause()
    }

    override fun onResume() {
        super.onResume()

        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDestroy()
    }
}

