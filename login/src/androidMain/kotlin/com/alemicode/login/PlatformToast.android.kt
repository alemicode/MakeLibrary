package com.alemicode.login

import android.content.Context
import android.widget.Toast

actual fun showToast(context: Any?, message: String) {
    (context as? Context)?.let {
        Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
    }
}
