package com.databindingwithrecyclerview.model

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField


class RegisterViewModel : ViewModel() {


    val name:ObservableField<String> = ObservableField("")
//    val name = ObservableField<String>()
    val password = ObservableField<String>()
    val confirmPassword = ObservableField<String>()
    val nameError = ObservableField<String>()
    val passwordError = ObservableField<String>()
    val confirmPasswordError = ObservableField<String>()
    val nameErrorEnable = ObservableField<Boolean>()
    val passwordErrorEnable = ObservableField<Boolean>()
    val confirmPasswordErrorEnable = ObservableField<Boolean>()


    fun validateInputs(): Boolean {
        var isValid = true

       if (name.get() == null ) {
            nameError.set("Invalid Email")
            nameErrorEnable.set(true)
            isValid = false

        } else {
            nameError.set(null)
            nameErrorEnable.set(false)
        }

        if (password.get() == null || password.get()!!.length < 4) {
            passwordError.set("Password too short")
            passwordErrorEnable.set(true)
            isValid = false
        } else {
            passwordError.set(null)
            passwordErrorEnable.set(false)
        }

        if (confirmPassword.get() == null || confirmPassword != password) {
            confirmPasswordError.set("confirmPassword not match")
            confirmPasswordErrorEnable.set(true)
            isValid = false
        } else {
            confirmPasswordError.set(null)
            confirmPasswordErrorEnable.set(false)
        }

        return isValid
    }

}