package com.farsitel.panjere.profile.model

import androidx.compose.runtime.Composable
import com.farsitel.panjere.core.extension.toNetworkException
import com.farsitel.panjere.core.model.UiState
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.profile.component.ProfileBookmarkWidget
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileBookmarkItem : PanjereComposeItem, ProfileCommunicable {

    private var onBookmarkRowClicked: () -> Unit = {}
    private var onRetryBookmarkRowClicked: () -> Unit = {}

    private val _bookmarkState: MutableStateFlow<UiState<List<String>>> =
        MutableStateFlow(UiState.Loading)

    private val bookmarkState = _bookmarkState.asStateFlow()

    fun onRequestSent() {
        _bookmarkState.value = UiState.Loading
    }

    fun onBookmarkRequestFailed(throwable: Throwable) {
        _bookmarkState.value = UiState.Failure(throwable.toNetworkException())
    }

    fun onBookmarkRequestSucceed(bookmarkList: List<String>) {
        _bookmarkState.value = UiState.Success(bookmarkList)
    }

    @Composable
    override fun ComposeView() {
        ProfileBookmarkWidget(
            bookmarkState,
            onBookmarkRowClicked,
            onRetryBookmarkRowClicked
        )
    }

    override fun setCommunicator(communicator: ProfileCommunicator) {
        onBookmarkRowClicked = communicator.onBookmarkClicked
        onRetryBookmarkRowClicked = communicator.onRetryBookmarkClicked
    }
}