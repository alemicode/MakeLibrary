package com.farsitel.panjere.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.farsitel.panjere.designsystem.button.ButtonState
import com.farsitel.panjere.designsystem.button.filled.wrap.secondary.WrapMediumFilledSecondaryButton
import com.farsitel.panjere.designsystem.button.outline.wrap.WrapMediumOutlineButton
import com.farsitel.panjere.designsystem.theme.space
import com.farsitel.panjere.profile.R

@Composable
fun LogoutContent(
    isLogoutButtonLoading: State<Boolean>,
    onLogoutClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = MaterialTheme.space.xMedium)
    ) {
        Text(
            text = stringResource(R.string.label_logout),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )
        Text(
            text = stringResource(R.string.bottomsheet_description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(MaterialTheme.space.large))
        Row(modifier = Modifier.padding(vertical = MaterialTheme.space.small)) {
            WrapMediumFilledSecondaryButton(
                modifier = Modifier.weight(2f),
                label = stringResource(R.string.bottomsheet_logout),
                onClick = onLogoutClick,
                buttonState = ButtonState(showLoading = isLogoutButtonLoading.value)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.space.small))
            WrapMediumOutlineButton(
                modifier = Modifier.weight(1f),
                label = stringResource(R.string.bottomsheet_logout_cancel),
                onClick = onCancelClick,
            )
        }
    }
}