package com.farsitel.panjere.profile.datasource

import com.farsitel.panjere.account.model.ProfileModel
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.profile.R
import com.farsitel.panjere.profile.model.ProfileBookmarkItem
import com.farsitel.panjere.profile.model.ProfileDividerItem
import com.farsitel.panjere.profile.model.ProfileInfo
import com.farsitel.panjere.profile.model.ProfileLoginModel
import com.farsitel.panjere.profile.model.ProfileLogoutItem
import com.farsitel.panjere.profile.model.ProfileRateItem
import com.farsitel.panjere.profile.model.ProfileSimpleLinkItem
import com.farsitel.panjere.profile.model.ProfileTooltip
import javax.inject.Inject

internal class ProfileLocalDataSource @Inject constructor() {

    fun provideProfileData(
        userLoggedIn: Boolean,
        userProfiler: ProfileModel?,
        isBazaarInstalled: Boolean,
    ): List<PanjereComposeItem> {
        val result = mutableListOf<PanjereComposeItem>()
        addUserSection(userLoggedIn, result, userProfiler)

        if (userLoggedIn) {
            result.add(ProfileBookmarkItem())
        }
        result.add(
            ProfileSimpleLinkItem(
                R.drawable.ic_profile_law,
                R.string.label_usage_law,
                R.string.link_usage_law
            )
        )
        result.add(
            ProfileSimpleLinkItem(
                R.drawable.ic_profile_privacy,
                R.string.label_privacy,
                R.string.link_privacy
            )
        )
        result.add(
            ProfileSimpleLinkItem(
                R.drawable.ic_profile_support,
                R.string.label_support,
                R.string.link_support
            )
        )

        if (isBazaarInstalled) {
            result.add(ProfileDividerItem())
            result.add(ProfileRateItem())
        }

        if (userLoggedIn) {
            result.add(ProfileDividerItem())
            result.add(ProfileLogoutItem())
        }

        return result
    }

    private fun addUserSection(
        userLoggedIn: Boolean,
        result: MutableList<PanjereComposeItem>,
        userProfiler: ProfileModel?
    ) {
        if (userLoggedIn) {
            if (requireNotNull(userProfiler).fullName.isNullOrEmpty()) {
                result.add(
                    ProfileTooltip(R.string.tooltip_complete_profile)
                )
            }
            result.add(
                ProfileInfo(
                    requireNotNull(userProfiler.phoneNumber),
                    userProfiler.fullName
                )
            )
        } else {
            result.add(
                ProfileTooltip(R.string.tooltip_login)
            )
            result.add(ProfileLoginModel())
        }
    }
}