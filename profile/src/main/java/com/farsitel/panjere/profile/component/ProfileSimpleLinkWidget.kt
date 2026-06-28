package com.farsitel.panjere.profile.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource

@Composable
fun ProfileSimpleLinkWidget(
    imageResId: Int,
    textResId: Int,
    clickLinkResId: Int,
    onItemClicked: (String) -> Unit
) {

    val clickLink = stringResource(clickLinkResId)
    val onSimpleItemClicked = remember {
        { onItemClicked.invoke(clickLink) }
    }
    ProfileSimpleItemWidget(
        imageResId = imageResId,
        textResId = textResId,
        onItemClicked = onSimpleItemClicked
    )
}