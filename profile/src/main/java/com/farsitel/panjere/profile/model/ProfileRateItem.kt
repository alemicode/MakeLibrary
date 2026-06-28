package com.farsitel.panjere.profile.model

import androidx.compose.runtime.Composable
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.profile.R
import com.farsitel.panjere.profile.component.ProfileSimpleItemWidget

class ProfileRateItem : PanjereComposeItem, ProfileCommunicable {

    private var onItemClicked: () -> Unit = {}

    @Composable
    override fun ComposeView() {
        ProfileSimpleItemWidget(
            R.drawable.ic_star,
            R.string.label_rate,
            onItemClicked
        )
    }

    override fun setCommunicator(communicator: ProfileCommunicator) {
        onItemClicked = communicator.onRateClicked
    }
}