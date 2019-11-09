package com.here.activetimetracker

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.here.activetimetracker.model.CounterViewModel
import kotlinx.android.synthetic.main.content_active_screen.*

class MainActivity : BaseActivity<com.here.activetimetracker.databinding.ContentActiveScreenBinding>(), LifecycleOwner {

    override fun getLayoutRes(): Int {
        return R.layout.content_active_screen
    }

    val counterViewModel: CounterViewModel by lazy {
        ViewModelProviders.of(this).get(CounterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(counterViewModel)

        counterViewModel.counterValue.observe(this, Observer {
            dataBinding.chronometer.base = it
            dataBinding.chronometer.start()
        })

        dataBinding.chronometer.setOnChronometerTickListener {
            counterViewModel.onTick(it.base)
        }

        counterViewModel.chronometerFormat.observe(this, Observer {
            chronometer.format = it
        })
    }

    override fun onPause() {
        super.onPause()
        dataBinding.chronometer.stop()
    }
}
