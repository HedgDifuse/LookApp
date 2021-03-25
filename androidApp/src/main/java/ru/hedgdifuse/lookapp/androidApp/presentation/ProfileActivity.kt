package ru.hedgdifuse.lookapp.androidApp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.hedgdifuse.lookapp.androidApp.R
import ru.hedgdifuse.lookapp.androidApp.databinding.ActivityProfileBinding
import ru.hedgdifuse.lookapp.androidApp.presentation.base.binding.BindingActivity
import ru.hedgdifuse.lookapp.shared.base.render.RenderState
import ru.hedgdifuse.lookapp.shared.presentation.profile.ProfilePresenterI
import ru.hedgdifuse.lookapp.shared.presentation.profile.ProfileViewI

/**
 * [ProfileActivity] - second activity, title is displayed here
 */
class ProfileActivity: BindingActivity<ActivityProfileBinding>(
    ActivityProfileBinding::inflate
), ProfileViewI {

    override val presenter: ProfilePresenterI by inject { parametersOf(this) }

    /**
     * Render state, like MVI
     */
    override var renderState: RenderState = RenderState.LOADING
        set(value) {

            binding.errorLayout.root.visibility =
                if (value == RenderState.ERROR) View.VISIBLE
                else View.GONE

            binding.contentLayout.visibility =
                if (value == RenderState.LOADED) View.VISIBLE
                else View.GONE

            binding.loadingLayout.visibility =
                if (value == RenderState.LOADING) View.VISIBLE
                else View.GONE

            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.onStart()

        binding.errorLayout.errorRetryButton.setOnClickListener {
            presenter.refresh()
        }
    }

    /**
     * Presenter methods
     */
    override fun submitTitle(title: String) {
        binding.profileTitle.text = getString(R.string.profile_title).format(title)
    }

    override fun goToMainView() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}