package com.luckerdeveloper.yesnomemes

import android.app.Application
import com.luckerdeveloper.yesnomemes.di.AppComponent
import com.luckerdeveloper.yesnomemes.di.DaggerAppComponent

class MainApp : Application() {

    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent
        get() = checkNotNull(_appComponent) {
            "AppComponent isn't initialized"
        }


    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.create()
    }
}
