package com.farsitel.panjere.profile.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.farsitel.panjere.designsystem.bottomsheet.PanjereModalBottomSheetComponent
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.designsystem.theme.space
import com.farsitel.panjere.profile.model.ProfileCommunicable
import com.farsitel.panjere.profile.model.ProfileCommunicator
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun ProfileScreen(
    logoutButtonLoadingState: StateFlow<Boolean>,
    profileDataState: StateFlow<List<PanjereComposeItem>>,
    profileCommunicator: ProfileCommunicator,
    panjereModalBottomSheetComponent: PanjereModalBottomSheetComponent
) {

    val isLogoutButtonLoading = logoutButtonLoadingState.collectAsStateWithLifecycle()
    val onLogoutClicked: () -> Unit = remember {
        {
            panjereModalBottomSheetComponent.showBottomSheet {
                LogoutContent(
                    onLogoutClick = profileCommunicator.onConfirmLogoutClicked,
                    onCancelClick = profileCommunicator.onCancelLogoutClicked,
                    isLogoutButtonLoading = isLogoutButtonLoading
                )
            }
        }
    }
    profileCommunicator.onLogoutClicked = onLogoutClicked

    ProfileContent(
        profileDataState,
        profileCommunicator
    )
}

@Composable
@OptIn(ExperimentalLifecycleComposeApi::class)
private fun ProfileContent(
    profileDataState: StateFlow<List<PanjereComposeItem>>,
    profileCommunicator: ProfileCommunicator
) {
    val profileData by profileDataState.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = MaterialTheme.space.medium)
    ) {
        items(
            count = profileData.size,
            contentType = { index -> profileData[index].javaClass.name }
        ) { index ->
            val item = profileData[index]
            (item as? ProfileCommunicable)?.setCommunicator(profileCommunicator)
            item.ComposeView()
        }
    }
}