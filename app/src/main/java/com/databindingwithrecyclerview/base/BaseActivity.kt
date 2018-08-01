package com.databindingwithrecyclerview.base

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

/**
 * @author ihsan on 11/29/17.
 */

abstract class BaseActivity<M : ViewModel, B : ViewDataBinding> : AppCompatActivity() {

    protected abstract val viewModel: Class<M>

    @get:LayoutRes
    protected abstract val layoutResId: Int


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, layoutResId)
        val viewModel = ViewModelProviders.of(this).get(viewModel)
//        binding.setLifecycleOwner(this)
        onCreate(savedInstanceState, viewModel, binding as B)
    }

    protected abstract fun onCreate(instance: Bundle?, viewModel: M, binding: B)

}
