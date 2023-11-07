package com.luckerdeveloper.yesnomemes.di

import com.luckerdeveloper.yesnomemes.MainActivity
import dagger.Component

@Component
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
