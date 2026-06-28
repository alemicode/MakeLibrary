package com.farsitel.panjere.profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.farsitel.panjere.designsystem.component.ContentDescription
import com.farsitel.panjere.designsystem.preview.ThemePreview
import com.farsitel.panjere.designsystem.theme.space
import com.farsitel.panjere.profile.R
import com.farsitel.panjere.designsystem.R as DR

@Composable
internal fun ProfileLoginWidget(
    onLoginClicked: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .clickable(
                interactionSource,
                indication = null
            ) { onLoginClicked.invoke() }
            .padding(horizontal = MaterialTheme.space.medium)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall,
            text = stringResource(id = R.string.login_into_account)
        )

        Icon(
            painter = painterResource(id = DR.drawable.ic_login),
            contentDescription = ContentDescription.LOGIN,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(MaterialTheme.space.tiny)
        )
    }
}

@ThemePreview
@Composable
fun PreviewProfileLoginWidget() {
    ProfileLoginWidget {}
}