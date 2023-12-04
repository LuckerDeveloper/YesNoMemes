package com.luckerdeveloper.yesnomemes.di

import com.luckerdeveloper.network.di.NetworkModule
import com.luckerdeveloper.yesnomemes.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
