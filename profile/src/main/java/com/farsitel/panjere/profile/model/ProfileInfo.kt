package com.farsitel.panjere.profile.model

import androidx.compose.runtime.Composable
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.profile.component.ProfileInfoWidget

class ProfileInfo(
    private val phoneNumber: String,
    var fullName: String?
) : PanjereComposeItem, ProfileCommunicable {

    private var onEditClicked: () -> Unit = {}

    @Composable
    override fun ComposeView() {
        ProfileInfoWidget(
            phoneNumber,
            fullName,
            onEditClicked
        )
    }

    override fun setCommunicator(communicator: ProfileCommunicator) {
        onEditClicked = communicator.onEditProfileClicked
    }
}