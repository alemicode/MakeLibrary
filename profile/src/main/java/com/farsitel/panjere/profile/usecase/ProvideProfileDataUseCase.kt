package com.farsitel.panjere.profile.usecase

import com.farsitel.panjere.account.usecase.UserUseCase
import com.farsitel.panjere.designsystem.model.PanjereComposeItem
import com.farsitel.panjere.profile.datasource.PackageInstallDataSource
import com.farsitel.panjere.profile.datasource.ProfileLocalDataSource
import javax.inject.Inject

private const val BAZAAR_PACKAGE_NAME = "com.farsitel.bazaar"

internal class ProvideProfileDataUseCase @Inject constructor(
    private val userUseCase: UserUseCase,
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val packageInstallDataSource: PackageInstallDataSource
) {

    suspend operator fun invoke(): List<PanjereComposeItem> {
        return profileLocalDataSource.provideProfileData(
            userUseCase.isUserLoggedIn(),
            userUseCase.getUserProfile(),
            packageInstallDataSource.isPackageInstalled(BAZAAR_PACKAGE_NAME)
        )
    }
}