package com.databindingwithrecyclerview.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.databindingwithrecyclerview.R
import com.databindingwithrecyclerview.base.BaseFragment
import com.databindingwithrecyclerview.databinding.FragmentRegisterBinding
import com.databindingwithrecyclerview.model.RegisterViewModel
import android.view.View
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {


    private var regBinding: FragmentRegisterBinding? = null

    override val viewModel: Class<RegisterViewModel>
        get() = RegisterViewModel::class.java
    override val layoutResId: Int
        get() = R.layout.fragment_register

    override fun onCreateView(container: ViewGroup?, savedInstanceState: Bundle?, viewModel: RegisterViewModel, binding: FragmentRegisterBinding) {
        binding.registerViewModel = viewModel
        this.regBinding = binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignUp.setOnClickListener {
            if (regBinding!!.registerViewModel!!.validateInputs()) {
            }
        }
    }



}

