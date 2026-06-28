package com.farsitel.panjere.profile.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.farsitel.panjere.designsystem.preview.ThemePreview
import com.farsitel.panjere.designsystem.theme.space

@Composable
internal fun ProfileDividerWidget() {
    Box(modifier = Modifier.padding(MaterialTheme.space.medium)) {
        Divider(
            color = MaterialTheme.colorScheme.surface
        )
    }
}

@ThemePreview
@Composable
fun PreviewProfileDividerWidget() {
    ProfileDividerWidget()
}