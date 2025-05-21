package com.android.starzplay.presentation.navigation

import com.android.starzplay.utils.NavigationConstants.VIDEO_URL_KEY

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Details : Screen("details")
    object Play : Screen("play/{$VIDEO_URL_KEY}")
    object Language : Screen("language")
}