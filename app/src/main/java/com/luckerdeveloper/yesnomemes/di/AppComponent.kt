package com.luckerdeveloper.yesnomemes.di

import com.luckerdeveloper.network.di.NetworkModule
import com.luckerdeveloper.yesnomemes.MainActivity
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
