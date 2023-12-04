package com.luckerdeveloper.yes_no_request

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val yesNoNavigationRoute = "yes_no_route"

fun NavController.navigateToYesNo(navOptions: NavOptions? = null) {
    this.navigate(com.luckerdeveloper.yes_no_request.yesNoNavigationRoute, navOptions)
}

fun NavGraphBuilder.yesNoScreen(viewModelImpl: YesNoViewModelImpl) {
    composable(
        route = com.luckerdeveloper.yes_no_request.yesNoNavigationRoute,
    ) {
        YesNoRoute(viewModelImpl)
    }
}
