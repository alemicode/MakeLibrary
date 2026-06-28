package com.farsitel.panjere.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.farsitel.panjere.designsystem.bottomsheet.PanjereModalBottomSheetComponent
import com.farsitel.panjere.profile.ui.ProfileRoute

internal const val PROFILE_ROUTE_PREFIX = "profile"
const val PROFILE_NAVIGATION_ROUTE = "${PROFILE_ROUTE_PREFIX}_route"

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    navigate(route = PROFILE_NAVIGATION_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.profileScreen(
    showSnackbar: (String) -> Unit,
    navigateToLogin: () -> Unit,
    navigateToBookmark: () -> Unit,
    navigateToEditProfile: () -> Unit,
    panjereModalBottomSheetComponent: PanjereModalBottomSheetComponent
) {
    composable(route = PROFILE_NAVIGATION_ROUTE) {
        ProfileRoute(
            showSnackbar,
            navigateToLogin,
            navigateToBookmark,
            navigateToEditProfile,
            panjereModalBottomSheetComponent
        )
    }
}