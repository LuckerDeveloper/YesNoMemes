package com.luckerdeveloper.yesnomemes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.luckerdeveloper.yes_no_request.yesNoNavigationRoute
import com.luckerdeveloper.yes_no_request.yesNoScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = yesNoNavigationRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        yesNoScreen()
    }
}
