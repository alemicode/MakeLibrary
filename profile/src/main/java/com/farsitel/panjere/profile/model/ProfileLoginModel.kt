package com.farsitel.panjere.profile.model

import androidx.compose.runtime.Composable
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.profile.component.ProfileLoginWidget

class ProfileLoginModel : PanjereComposeItem, ProfileCommunicable {

    private var onLoginClicked: () -> Unit = {}

    @Composable
    override fun ComposeView() {
        ProfileLoginWidget(onLoginClicked)
    }

    override fun setCommunicator(communicator: ProfileCommunicator) {
        onLoginClicked = communicator.onLoginClicked
    }
}