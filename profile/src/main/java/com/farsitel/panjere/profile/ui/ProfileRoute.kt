package com.farsitel.panjere.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.farsitel.panjere.core.extension.openRateIntent
import com.farsitel.panjere.core.extension.openUrl
import com.farsitel.panjere.designsystem.bottomsheet.PanjereModalBottomSheetComponent
import com.farsitel.panjere.profile.model.ProfileCommunicator
import com.farsitel.panjere.profile.viewmodel.ProfileViewModel

@Composable
internal fun ProfileRoute(
    showSnackbar: (String) -> Unit,
    navigateToLogin: () -> Unit,
    navigateToBookmark: () -> Unit,
    navigateToEditProfile: () -> Unit,
    panjereModalBottomSheetComponent: PanjereModalBottomSheetComponent,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val openIntent: (String) -> Unit = remember { { link -> context.openUrl(link) } }

    val onRateClicked: () -> Unit = remember { { context.openRateIntent() } }

    LaunchedEffect(key1 = Unit) {
        profileViewModel.errorMessageState.collect { errorMessage ->
            if (errorMessage != null) {
                panjereModalBottomSheetComponent.hideBottomSheet()

                when {
                    errorMessage.message.isNullOrEmpty().not() -> {
                        errorMessage.message
                    }
                    errorMessage.messageResId != null -> {
                        context.getString(requireNotNull(errorMessage.messageResId))
                    }
                    else -> null
                }?.let { message ->
                    showSnackbar.invoke(message)
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        profileViewModel.hideBottomSheet.collect {
            panjereModalBottomSheetComponent.hideBottomSheet()
        }
    }

    val profileCommunicator = ProfileCommunicator(
        onLoginClicked = navigateToLogin,
        onEditProfileClicked = navigateToEditProfile,
        onOpenLinkClicked = openIntent,
        onBookmarkClicked = navigateToBookmark,
        onRetryBookmarkClicked = profileViewModel::onRetryBookmarkClicked,
        onRateClicked = onRateClicked,
        onConfirmLogoutClicked = profileViewModel::onConfirmLogoutClicked,
        onCancelLogoutClicked = panjereModalBottomSheetComponent::hideBottomSheet
    )

    ProfileScreen(
        profileViewModel.logoutLoadingState,
        profileViewModel.profileDataState,
        profileCommunicator,
        panjereModalBottomSheetComponent
    )
}