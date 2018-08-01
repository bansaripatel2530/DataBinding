package com.databindingwithrecyclerview.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import com.databindingwithrecyclerview.R
import com.databindingwithrecyclerview.base.BaseFragment
import com.databindingwithrecyclerview.databinding.FragmentLoginBinding
import com.databindingwithrecyclerview.model.LoginViewModel

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override val viewModel: Class<LoginViewModel>
        get() = LoginViewModel::class.java


    override val layoutResId: Int
        get() = R.layout.fragment_login

    override fun onCreateView(container: ViewGroup?, savedInstanceState: Bundle?, viewModel: LoginViewModel, binding: FragmentLoginBinding) {
        binding.loginViewModel = viewModel
    }

}