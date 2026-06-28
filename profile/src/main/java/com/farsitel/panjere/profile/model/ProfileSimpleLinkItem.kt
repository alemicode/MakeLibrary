package com.farsitel.panjere.profile.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.profile.component.ProfileSimpleLinkWidget

class ProfileSimpleLinkItem(
    @DrawableRes val imageResId: Int,
    @StringRes val textResId: Int,
    @StringRes val clickLinkResId: Int
) : PanjereComposeItem, ProfileCommunicable {

    private var onItemClicked: (String) -> Unit = {}

    @Composable
    override fun ComposeView() {
        ProfileSimpleLinkWidget(
            imageResId,
            textResId,
            clickLinkResId,
            onItemClicked
        )
    }

    override fun setCommunicator(communicator: ProfileCommunicator) {
        onItemClicked = communicator.onOpenLinkClicked
    }
}