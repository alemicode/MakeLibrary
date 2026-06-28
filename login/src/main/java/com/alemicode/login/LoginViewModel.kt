package com.alemicode.login

import android.content.Context
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    fun onSendClicked(context: Context, username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            showToast(context, "Username and password cannot be empty")
            return
        }
        
        if (password.length < 6) {
            showToast(context, "Password must be at least 6 characters")
            return
        }
        
        showToast(context, "Login successful for $username")
    }
}
