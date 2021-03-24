package ru.hedgdifuse.lookapp.androidApp.presentation

import android.os.Bundle
import ru.hedgdifuse.lookapp.androidApp.databinding.ActivityMainBinding
import ru.hedgdifuse.lookapp.androidApp.presentation.base.binding.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.textView.text = "Hello World"
    }
}
