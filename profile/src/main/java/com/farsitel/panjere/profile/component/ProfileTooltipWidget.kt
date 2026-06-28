package com.farsitel.panjere.profile.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.farsitel.panjere.designsystem.preview.ThemePreview
import com.farsitel.panjere.designsystem.theme.space
import com.farsitel.panjere.profile.R

@Composable
internal fun ProfileTooltipWidget(
    @StringRes messageResId: Int
) {
    Row(
        modifier = Modifier.padding(horizontal = MaterialTheme.space.small),
        verticalAlignment = Alignment.Bottom
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(bottom = MaterialTheme.space.small)
                .clip(
                    RoundedCornerShape(
                        topStart = MaterialTheme.space.small,
                        topEnd = MaterialTheme.space.small,
                        bottomStart = MaterialTheme.space.small
                    )
                )
                .background(MaterialTheme.colorScheme.primary)
                .padding(
                    vertical = MaterialTheme.space.xSmall,
                    horizontal = MaterialTheme.space.small
                ),
            text = stringResource(id = messageResId)
        )
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
@ThemePreview
fun PreviewProfileTooltipWidget() {
    ProfileTooltipWidget(messageResId = R.string.tooltip_login)
}