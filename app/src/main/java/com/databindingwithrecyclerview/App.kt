package com.databindingwithrecyclerview

import android.app.Application
import android.arch.persistence.room.Room
import com.databindingwithrecyclerview.db.AppDao
import com.databindingwithrecyclerview.db.AppDatabase
import com.shopomy.webservice.ApiService

/**
 * Created by Kinjal Dhamat on 6/12/2018.
 */
class App : Application() {
    private var appDao: AppDao? = null

    var apiService: ApiService? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        apiService = ApiService()
        appDao = Room.databaseBuilder(this, AppDatabase::class.java, "demo.db")
                .allowMainThreadQueries()
                .build().appDeo()

    }

    override fun onTerminate() {
        super.onTerminate()
        if (instance != null) {
            instance = null
        }
    }

    companion object {
        var instance: App? = null
            private set
    }

    fun getAppDeo(): AppDao? {
        return appDao
    }


}
