package com.farsitel.panjere.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farsitel.panjere.account.model.ProfileModel
import com.farsitel.panjere.account.usecase.UserUseCase
import com.farsitel.panjere.bookmark.repository.BookmarkRepository
import com.farsitel.panjere.core.extension.toPanjereException
import com.farsitel.panjere.core.mapper.safeMap
import com.farsitel.panjere.core.model.exception.PanjereException
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.profile.model.ProfileBookmarkItem
import com.farsitel.panjere.profile.model.ProfileInfo
import com.farsitel.panjere.profile.model.ProfileLogoutItem
import com.farsitel.panjere.profile.model.ProfileTooltip
import com.farsitel.panjere.profile.usecase.ProvideProfileDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val bookmarkRepository: BookmarkRepository,
    private val provideProfileDataUseCase: ProvideProfileDataUseCase
) : ViewModel() {

    private val _profileDataState = MutableStateFlow(listOf<PanjereComposeItem>())
    val profileDataState: StateFlow<List<PanjereComposeItem>> = _profileDataState.asStateFlow()

    private val _logoutLoadingState = MutableStateFlow(false)
    val logoutLoadingState: StateFlow<Boolean> = _logoutLoadingState.asStateFlow()

    private val _errorMessageState = MutableSharedFlow<PanjereException?>()
    val errorMessageState: SharedFlow<PanjereException?> = _errorMessageState.asSharedFlow()

    private val _hideBottomSheet = MutableSharedFlow<Unit>()
    val hideBottomSheet: SharedFlow<Unit> = _hideBottomSheet.asSharedFlow()

    init {
        listenOnProfileChange()
    }

    private fun listenOnProfileChange() {
        viewModelScope.launch {
            userUseCase.getUserProfileFlow().collect { profileModel ->
                when {
                    _profileDataState.value.isEmpty() -> {
                        fetchProfileData()
                    }
                    itemsIsOutDated(profileModel) -> {
                        fetchProfileData()
                    }
                    profileCompleted(profileModel) -> {
                        fetchProfileData()
                    }
                    else -> {
                        updateProfileRow(profileModel)
                    }
                }
            }
        }
    }

    private fun updateProfileRow(profileModel: ProfileModel) {
        val profileInfoItem = getItemRowType<ProfileInfo>()
        profileInfoItem?.fullName = profileModel.fullName
    }

    private fun profileCompleted(profileModel: ProfileModel): Boolean {
        val profileTooltip = getItemRowType<ProfileTooltip>()
        return profileTooltip != null && profileModel.fullName.isNullOrEmpty().not()
    }

    private fun itemsIsOutDated(profileModel: ProfileModel): Boolean {
        val logoutRow = getItemRowType<ProfileLogoutItem>()
        // user is login but logout row is not exists
        return profileModel.phoneNumber.isNullOrEmpty().not() && logoutRow == null
    }

    private fun fetchProfileData() {
        viewModelScope.launch {
            _profileDataState.value = provideProfileDataUseCase.invoke()
            getBookmarkData()
        }
    }

    private fun getBookmarkData() {
        getBookmarkItem()?.onRequestSent()
        viewModelScope.launch {
            bookmarkRepository.getBookmarkedList(cursor = null).map { bookmarkList ->
                safeMap { bookmarkList.items.map { it.imageUrl } }
            }.fold(
                ::getBookmarkSucceed, ::getBookmarkFailed
            )
        }
    }

    private fun getBookmarkSucceed(imageList: List<String>) {
        getBookmarkItem()?.onBookmarkRequestSucceed(imageList)
    }

    private fun getBookmarkFailed(throwable: Throwable) {
        getBookmarkItem()?.onBookmarkRequestFailed(throwable)
    }

    private fun getBookmarkItem(): ProfileBookmarkItem? {
        return getItemRowType<ProfileBookmarkItem>()
    }

    private inline fun <reified T> getItemRowType(): T? {
        return _profileDataState.value
            .filterIsInstance(T::class.java)
            .firstOrNull()
    }

    fun onRetryBookmarkClicked() {
        getBookmarkData()
    }

    fun onConfirmLogoutClicked() {
        _logoutLoadingState.value = true
        viewModelScope.launch {
            userUseCase.logout().fold(::onLogoutSucceed, ::onLogoutFailed)
        }
    }

    private fun onLogoutSucceed(status: Boolean) {
        fetchProfileData()
        viewModelScope.launch {
            _logoutLoadingState.value = false
            _hideBottomSheet.emit(Unit)
        }
    }

    private fun onLogoutFailed(throwable: Throwable) {
        viewModelScope.launch {
            _logoutLoadingState.value = false
            _hideBottomSheet.emit(Unit)
            _errorMessageState.emit(throwable.toPanjereException())
        }
    }
}