package com.farsitel.panjere.profile.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.farsitel.panjere.core.model.UiState
import com.farsitel.panjere.designsystem.component.ContentDescription
import com.farsitel.panjere.designsystem.component.LoadingComponent
import com.farsitel.panjere.designsystem.image.PanjereImageLoader
import com.farsitel.panjere.designsystem.preview.ThemePreview
import com.farsitel.panjere.designsystem.theme.space
import com.farsitel.panjere.profile.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.min
import com.farsitel.panjere.designsystem.R as DR

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun ProfileBookmarkWidget(
    bookmarkStateFlow: StateFlow<UiState<List<String>>>,
    onBookmarkRowClicked: () -> Unit,
    onRetryBookmarkClicked: () -> Unit
) {

    val bookmarkState by bookmarkStateFlow.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .padding(MaterialTheme.space.medium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .clickable { onBookmarkRowClicked.invoke() }
            .padding(MaterialTheme.space.medium)
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.space.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextRow()
        when (bookmarkState) {
            is UiState.Loading -> {
                LoadingComponent(
                    modifier = Modifier
                        .padding(MaterialTheme.space.xSmall)
                        .size(32.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            is UiState.Failure -> {
                Icon(
                    painter = painterResource(id = DR.drawable.ic_retry),
                    contentDescription = ContentDescription.RETRY,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onRetryBookmarkClicked.invoke() }
                        .padding(MaterialTheme.space.small),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            is UiState.Success -> {
                BookmarkList((bookmarkState as UiState.Success).data)
            }
        }
    }
}

private const val BOOKMARK_ITEM_SIZE_IN_DP = 52

@Composable
private fun BookmarkList(data: List<String>) {
    if (data.isEmpty()) {
        return
    }

    val maxItemsCount = MaxItemsCount()
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.space.small)
    ) {
        items(min(data.size, maxItemsCount)) { index ->
            if (needToShowMoreItem(index, maxItemsCount)) {
                Icon(
                    modifier = Modifier
                        .size(BOOKMARK_ITEM_SIZE_IN_DP.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(MaterialTheme.space.medium),
                    painter = painterResource(id = R.drawable.ic_bookmark_more),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = ContentDescription.MORE
                )
            } else {
                PanjereImageLoader(
                    modifier = Modifier
                        .size(BOOKMARK_ITEM_SIZE_IN_DP.dp)
                        .clip(MaterialTheme.shapes.small),
                    imageUrl = data[index]
                )
            }
        }
    }
}

private fun needToShowMoreItem(index: Int, maxItemsCount: Int): Boolean {
    return index == maxItemsCount - 1
}

@Composable
private fun MaxItemsCount(): Int {
    val itemsSizeInDp = BOOKMARK_ITEM_SIZE_IN_DP
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val boxSize = screenWidth - 16 - 16
    return boxSize / (itemsSizeInDp + 16)
}

@Composable
internal fun TextRow() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(id = DR.string.profile_bookmark),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Icon(
            painter = painterResource(id = DR.drawable.ic_arrow_forward),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(20.dp)
                .padding(MaterialTheme.space.xSmall)
        )
    }
}

@Composable
@ThemePreview
fun PreviewProfileBookmarkWidget() {
    ProfileBookmarkWidget(
        bookmarkStateFlow = MutableStateFlow(UiState.Success(listOf())),
        onBookmarkRowClicked = {}) {}
}

@Composable
@ThemePreview
fun PreviewProfileBookmarkWidgetLoading() {
    ProfileBookmarkWidget(
        bookmarkStateFlow = MutableStateFlow(UiState.Loading),
        onBookmarkRowClicked = {}) {}
}

@Composable
@ThemePreview
fun PreviewProfileBookmarkWidgetSucces() {
    ProfileBookmarkWidget(
        bookmarkStateFlow = MutableStateFlow(
            UiState.Success(
                listOf("", "", "", "", "", "", "")
            )
        ),
        onBookmarkRowClicked = {}) {}
}
