package com.databindingwithrecyclerview.model

import android.arch.lifecycle.ViewModel
import com.databindingwithrecyclerview.base.BaseViewModel
import android.databinding.ObservableField



class LoginViewModel:ViewModel(){

    val email = ObservableField("")
    val password = ObservableField("")
}