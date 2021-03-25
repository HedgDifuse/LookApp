package ru.hedgdifuse.lookapp.androidApp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.hedgdifuse.lookapp.androidApp.databinding.ActivityMainBinding
import ru.hedgdifuse.lookapp.androidApp.presentation.base.binding.BindingActivity
import ru.hedgdifuse.lookapp.shared.base.render.RenderState
import ru.hedgdifuse.lookapp.shared.presentation.LaunchManager
import ru.hedgdifuse.lookapp.shared.presentation.code.CodePresenterI
import ru.hedgdifuse.lookapp.shared.presentation.code.CodeViewI

/**
 * [MainActivity] - first activity, code generation happens here
 */
class MainActivity : BindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate), CodeViewI {

    override var renderState = RenderState.LOADED
        set(value) {

            binding.errorLayout.root.visibility =
                if (value == RenderState.ERROR) View.VISIBLE
                else View.GONE

            binding.contentLayout.visibility =
                if (value == RenderState.LOADED) View.VISIBLE
                else View.GONE

            field = value
        }

    /**
     * Inject needed classes to here, using Koin
     */
    override val presenter: CodePresenterI by inject { parametersOf(this) }
    private val launchManager: LaunchManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!launchManager.isCodeScreen()) {
            startActivity(Intent(this, ProfileActivity::class.java))
            return finish()
        }

        presenter.onStart()

        binding.errorLayout.errorRetryButton.setOnClickListener {
            presenter.refresh()
        }
    }

    /**
     * Presenter required methods
     */
    override fun submitCode(code: Int) {
        binding.mainCode.text = code.toString()
    }

    override fun goToProfileScreen() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }
}
