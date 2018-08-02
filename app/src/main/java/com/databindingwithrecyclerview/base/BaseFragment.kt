package com.databindingwithrecyclerview.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Kinjal Dhamat on 6/12/2018.
 */
abstract class BaseFragment<M : ViewModel, B : ViewDataBinding> : Fragment() {


    protected abstract val viewModel: Class<M>

    @get:LayoutRes
    protected abstract val layoutResId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(layoutResId, container, false)

        val newsDetailsViewModel = ViewModelProviders.of(this).get(viewModel)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(container!!.context), layoutResId, container, false)
        binding.setLifecycleOwner(this)

        onCreateView(container, savedInstanceState, newsDetailsViewModel as M, binding as B)
        return binding.root
    }

    protected abstract fun onCreateView(container: ViewGroup?, savedInstanceState: Bundle?, viewModel: M, binding: B)

}
