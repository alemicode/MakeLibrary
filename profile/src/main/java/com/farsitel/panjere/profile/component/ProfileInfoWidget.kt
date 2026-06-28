package com.farsitel.panjere.profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.farsitel.panjere.designsystem.component.ContentDescription
import com.farsitel.panjere.designsystem.theme.space
import com.farsitel.panjere.designsystem.R as DR

@Composable
internal fun ProfileInfoWidget(
    phoneNumber: String,
    fullName: String?,
    onEditClicked: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .clickable(
                interactionSource,
                indication = null
            ) { onEditClicked.invoke() }
            .padding(horizontal = MaterialTheme.space.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {

            if (fullName.isNullOrEmpty().not()) {
                Text(
                    text = requireNotNull(fullName),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            val textColor = if (fullName.isNullOrEmpty()) {
                MaterialTheme.colorScheme.onBackground
            } else {
                MaterialTheme.colorScheme.onSurface
            }

            Text(
                text = phoneNumber,
                style = MaterialTheme.typography.headlineSmall,
                color = textColor
            )
        }

        Icon(
            modifier = Modifier.padding(MaterialTheme.space.xSmall),
            tint = MaterialTheme.colorScheme.onBackground,
            painter = painterResource(id = DR.drawable.ic_edit),
            contentDescription = ContentDescription.EDIT
        )
    }
}

@Composable
@Preview
fun PreviewProfileInfo() {
    ProfileInfoWidget(phoneNumber = "۰۹۱۲۶۲۳۴۵۶", fullName = null) {}
}

@Composable
@Preview
fun PreviewProfileInfoWithName() {
    ProfileInfoWidget(phoneNumber = "۰۱۴۲۳۵۶۳۴", fullName = "نام کاربری") {}
}