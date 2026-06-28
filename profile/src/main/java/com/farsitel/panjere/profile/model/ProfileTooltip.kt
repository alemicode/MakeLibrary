package com.farsitel.panjere.profile.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.profile.component.ProfileTooltipWidget

class ProfileTooltip(
    @StringRes val messageResId: Int
) : PanjereComposeItem {

    @Composable
    override fun ComposeView() {
        ProfileTooltipWidget(messageResId = messageResId)
    }
}