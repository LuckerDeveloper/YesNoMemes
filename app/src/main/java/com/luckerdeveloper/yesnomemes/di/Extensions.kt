package com.luckerdeveloper.yesnomemes.di

import android.content.Context
import com.luckerdeveloper.yesnomemes.MainApp

val Context.appComponent: AppComponent
    get() = when(this) {
        is MainApp -> this.appComponent
        else -> this.applicationContext.appComponent
    }