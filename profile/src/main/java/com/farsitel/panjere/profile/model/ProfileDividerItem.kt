package com.farsitel.panjere.profile.model

import androidx.compose.runtime.Composable
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.profile.component.ProfileDividerWidget

internal class ProfileDividerItem : PanjereComposeItem {

    @Composable
    override fun ComposeView() {
        ProfileDividerWidget()
    }
}