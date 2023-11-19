package com.luckerdeveloper.yesnomemes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.luckerdeveloper.yesnomemes.ui.theme.AppTheme
import com.luckerdeveloper.yesnomemes.ui.yesno.YesNoRoute
import com.luckerdeveloper.yesnomemes.ui.yesno.YesNoViewModelImpl
import dagger.Lazy
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var yesNoViewModelImplFactory: Lazy<YesNoViewModelImpl.Factory>

    private val viewModel: YesNoViewModelImpl by viewModels {
        yesNoViewModelImplFactory.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MainApp).appComponent.inject(this)
        setContent {
            AppTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = MaterialTheme.colorScheme.background,
                    darkIcons = !isSystemInDarkTheme()
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    YesNoRoute(viewModel)
                }
            }
        }
    }
}
