package com.farsitel.panjere.profile.model

import androidx.compose.runtime.Composable
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.profile.R
import com.farsitel.panjere.profile.component.ProfileSimpleItemWidget

class ProfileLogoutItem : PanjereComposeItem, ProfileCommunicable {

    private var onItemClicked: () -> Unit = {}

    @Composable
    override fun ComposeView() {
        ProfileSimpleItemWidget(
            R.drawable.ic_logout,
            R.string.label_logout,
            onItemClicked
        )
    }

    override fun setCommunicator(communicator: ProfileCommunicator) {
        onItemClicked = communicator.onLogoutClicked
    }
}