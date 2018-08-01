package com.shopomy.webservice

import android.content.Context
import com.databindingwithrecyclerview.App
import com.databindingwithrecyclerview.R
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Created by sa on 28/06/16.
 *
 *
 * This class for generating api call.
 */
object ApiHandle {

    fun <T> createRetrofitBase(observable: Observable<T>,
                               apiCallback: ApiCallback<T>) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<T>() {
                    override fun onNext(t: T) {
                        apiCallback.onSuccess(t)
                    }

                    override fun onError(error: Throwable) {
                        var responseModel: ApiErrorModel = ApiErrorModel()

                        when (error) {
                            is ConnectException -> {
                                responseModel!!.message = App.instance!!.getString(R.string.error_something_wrong)
                                responseModel.status = 400
                            }
                            is UnknownHostException -> {
                                responseModel!!.message = App.instance!!.getString(R.string.error_something_wrong)
                                responseModel.status = 400
                            }
                            is HttpException -> try {
                                val responseString = error.response().errorBody()!!.string()
                                if (responseString.contains("message")) {
                                    val gson = Gson()
                                    if (responseString.isNotEmpty()) {
                                        responseModel = gson.fromJson<ApiErrorModel>(responseString, ApiErrorModel::class.java!!)
                                        if (error.code() == 401) {
                                            responseModel.status = 401
                                        } else if (error.code() == 403) {
                                            responseModel.status = 403
                                        } else if (error.code() == 405) {
                                            responseModel.status = 405
                                        } else {
                                            responseModel.status = 400
                                        }
                                    }
                                } else {
                                    responseModel.message = App.instance!!.getString(R.string.error_something_wrong)
                                    responseModel.status = 400
                                }

                            } catch (e: IOException) {
                                responseModel.message = App.instance!!.getString(R.string.error_something_wrong)
                                responseModel.status = 400
                                e.printStackTrace()
                            }
                            else -> {
                                responseModel.message = App.instance!!.getString(R.string.error_something_wrong)
                                responseModel.status = 400
                            }
                        }
                        apiCallback.onFailure(responseModel)
                    }


                    override fun onComplete() {

                    }


                })
    }

    fun <T> createRetrofitBase(context: Context,
                               observable: Observable<T>,
                               apiCallback: ApiCallback<T>) {


        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<T>() {
                    override fun onNext(t: T) {
                        apiCallback.onSuccess(t)
                    }

                    override fun onError(error: Throwable) {
                        var responseModel: ApiErrorModel? = null
                        if (error is HttpException) {
                            try {
                                val response = error.response()
                                val gson = Gson()
                                val responseString = response.errorBody()!!.string()
                                responseModel = gson.fromJson<ApiErrorModel>(responseString, ApiErrorModel::class.java!!)
//                                DialogUtils.dialog(context, responseModel!!.message!!)
                            } catch (e: IOException) {
                                e.printStackTrace()
//                                DialogUtils.dialog(context, App.instance!!.getString(R.string.error_something_wrong))
                            }

                        } else {
//                            DialogUtils.dialog(context, App.instance!!.getString(R.string.error_something_wrong))
                        }
                        apiCallback.onFailure(responseModel!!)
//                        ProgressUtils.getInstance(context).close()
                    }


                    override fun onComplete() {

                    }


                })
    }

}
