package com.databindingwithrecyclerview.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.Observable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.ViewGroup
import com.databindingwithrecyclerview.R
import com.databindingwithrecyclerview.base.BaseFragment
import com.databindingwithrecyclerview.databinding.FragmentRegisterBinding
import com.databindingwithrecyclerview.model.RegisterViewModel
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.databindingwithrecyclerview.model.LoginViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.ArrayList
import android.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_login.*


class RegisterFragment:BaseFragment<RegisterViewModel,FragmentRegisterBinding>(){
    private  var registerViewModel:RegisterViewModel?=null


    override val viewModel: Class<RegisterViewModel>
        get() = RegisterViewModel::class.java
    override val layoutResId: Int
        get() = R.layout.fragment_register

    override fun onCreateView(container: ViewGroup?, savedInstanceState: Bundle?, viewModel: RegisterViewModel, binding: FragmentRegisterBinding) {
            this.registerViewModel =  ViewModelProviders.of(this).get(RegisterViewModel::class.java!!)
            binding.registerViewModel = this.registerViewModel
//
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignUp.setOnClickListener {
            if (registerViewModel!!.validateInputs()) {

            }
        }
    }

}

