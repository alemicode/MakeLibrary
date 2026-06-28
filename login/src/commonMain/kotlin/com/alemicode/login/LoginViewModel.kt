package com.alemicode.login

class LoginViewModel {
    fun onSendClicked(context: Any?, username: String, password: String) {
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
