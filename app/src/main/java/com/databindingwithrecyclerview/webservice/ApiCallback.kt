package com.shopomy.webservice

interface ApiCallback<T> {
    fun onSuccess(t: T)

    fun onFailure(apiErrorModel: ApiErrorModel)
}
