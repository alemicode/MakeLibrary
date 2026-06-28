package com.farsitel.panjere.profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.farsitel.panjere.designsystem.preview.ThemePreview
import com.farsitel.panjere.designsystem.theme.space
import com.farsitel.panjere.profile.R
import com.farsitel.panjere.designsystem.R as DR

@Composable
internal fun ProfileSimpleItemWidget(
    imageResId: Int,
    textResId: Int,
    onItemClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onItemClicked.invoke() }
            .padding(horizontal = MaterialTheme.space.medium),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.space.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )

        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = textResId),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Icon(
            painter = painterResource(id = DR.drawable.ic_arrow_forward),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .padding(MaterialTheme.space.xSmall)
        )
    }
}

@Composable
@ThemePreview
fun PreviewProfileSimpleItemWidget() {
    ProfileSimpleItemWidget(
        imageResId = DR.drawable.ic_category,
        textResId = R.string.login_into_account
    ) {}
}