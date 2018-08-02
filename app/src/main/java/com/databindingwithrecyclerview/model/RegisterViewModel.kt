package com.databindingwithrecyclerview.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


class RegisterViewModel : ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val confirmPassword: MutableLiveData<String> = MutableLiveData()
    val nameError: MutableLiveData<String> = MutableLiveData()
    val passwordError: MutableLiveData<String> = MutableLiveData()
    val confirmPasswordError: MutableLiveData<String> = MutableLiveData()
    val nameErrorEnable: MutableLiveData<Boolean> = MutableLiveData()
    val passwordErrorEnable: MutableLiveData<Boolean> = MutableLiveData()
    val confirmPasswordErrorEnable: MutableLiveData<Boolean> = MutableLiveData()

    init {
        name.postValue("")
        password.postValue("")
        confirmPassword.postValue("")
        nameError.postValue("")
        passwordError.postValue("")
        confirmPasswordError.postValue("")
        nameErrorEnable.postValue(false)
        passwordErrorEnable.postValue(false)
        confirmPasswordErrorEnable.postValue(false)
    }


    fun validateInputs(): Boolean {
        var isValid = true


        when {
            name.value == "" -> {
                nameErrorEnable.postValue(true)
                nameError.postValue("Please enter name")
                isValid = false
            }
            password.value == "" -> {
                nameErrorEnable.postValue(false)
                nameError.postValue(null)
                passwordError.postValue("Please enter password")
                passwordErrorEnable.postValue(true)
                isValid = false
            }
            password.value!!.length < 8 -> {
                passwordError.postValue("Password must be 8 character ")
                passwordErrorEnable.postValue(true)
            }
            confirmPassword.value == "" -> {
                passwordError.postValue(null)
                passwordErrorEnable.postValue(false)
                confirmPasswordError.postValue("Please enter confirm password")
                confirmPasswordErrorEnable.postValue(true)
                isValid = false
            }
            confirmPassword.value != password.value -> {
                confirmPasswordError.postValue("confirmPassword not match")
                confirmPasswordErrorEnable.postValue(true)
                isValid = false
            }
            else -> {
                confirmPasswordError.postValue(null)
                confirmPasswordErrorEnable.postValue(false)
                isValid = false

            }
        }


        return isValid
    }


}