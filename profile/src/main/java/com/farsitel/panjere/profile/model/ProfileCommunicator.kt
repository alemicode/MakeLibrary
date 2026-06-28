package com.farsitel.panjere.profile.model

class ProfileCommunicator(
    val onLoginClicked: () -> Unit,
    val onEditProfileClicked: () -> Unit,
    val onOpenLinkClicked: (String) -> Unit,
    val onBookmarkClicked: () -> Unit,
    val onRetryBookmarkClicked: () -> Unit,
    val onRateClicked: () -> Unit,
    val onConfirmLogoutClicked: () -> Unit,
    val onCancelLogoutClicked: () -> Unit
) {

    var onLogoutClicked: () -> Unit = {}
}

interface ProfileCommunicable {

    fun setCommunicator(communicator: ProfileCommunicator)
}