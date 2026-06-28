package com.farsitel.panjere.profile.datasource

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class PackageInstallDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun isPackageInstalled(packageName: String): Boolean {
        return try {
            context.packageManager.getPackageGids(packageName) != null
        } catch (ignored: Exception) {
            false
        }
    }
}