package com.example.destest.core.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.destest.core.kotlin.pass
import org.koin.androidx.compose.R

@SuppressLint("NewApi")
@Suppress("DEPRECATION")
fun Activity.hideSystemUI() {
    sdkBelow(Build.VERSION_CODES.R) {
        window.statusBarColor = Color.Black.copy(alpha = 0.01f).toArgb()
        window.navigationBarColor = Color.Black.copy(alpha = 0.01f).toArgb()
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            )
    }
    sdkEqualsOrAbove(Build.VERSION_CODES.R) {
        window.setDecorFitsSystemWindows(false)
        window.insetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        window.insetsController?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

@SuppressLint("NewApi")
@Suppress("DEPRECATION")
fun Activity.showSystemUI() {
    sdkBelow(Build.VERSION_CODES.R) {
        window.statusBarColor = TypedValue().apply { theme.resolveAttribute(R.attr.colorPrimaryDark, this, true) }.data
        window.navigationBarColor = TypedValue().apply { theme.resolveAttribute(android.R.attr.navigationBarColor, this, true) }.data
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            )
    }
    sdkEqualsOrAbove(Build.VERSION_CODES.R) {
        window.setDecorFitsSystemWindows(true)
        window.insetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
    }
}

private fun sdkBelow(version: Int, callback: () -> Unit) =
    if (Build.VERSION.SDK_INT < version) callback() else pass

private fun sdkEqualsOrAbove(version: Int, callback: () -> Unit) =
    if (Build.VERSION.SDK_INT >= version) callback() else pass
