package com.here.activetimetracker

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<D : ViewDataBinding/*, V : ViewModel*/> : AppCompatActivity() {

    lateinit var dataBinding: D

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView<D>(this, getLayoutRes())
        dataBinding.setLifecycleOwner(this)
    }
}