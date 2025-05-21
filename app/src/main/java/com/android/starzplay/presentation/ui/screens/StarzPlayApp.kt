package com.android.starzplay.presentation.ui.screens

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.starzplay.domain.models.MovieDomainDetails
import com.android.starzplay.presentation.components.decodeUrl
import com.android.starzplay.presentation.navigation.Screen
import com.android.starzplay.presentation.ui.activites.MainActivity
import com.android.starzplay.presentation.ui.screens.details.DetailsScreen
import com.android.starzplay.presentation.ui.screens.language.LanguageScreen
import com.android.starzplay.presentation.ui.screens.main.MainScreen
import com.android.starzplay.presentation.ui.screens.playerscreen.PlayerScreen
import com.android.starzplay.utils.NavigationConstants.MOVIE_ITEM
import com.android.starzplay.utils.NavigationConstants.VIDEO_URL_KEY
import com.android.starzplay.utils.localehelper.LanguageManager
import kotlinx.coroutines.CoroutineScope

@Composable
fun StarzPlayApp(languageManager: LanguageManager, coroutineScope: CoroutineScope) {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Screen.Main.route) {

        composable(Screen.Main.route) {
            MainScreen(
                languageManager = languageManager,
                onItemClick = { movie ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(MOVIE_ITEM, movie)
                    navController.navigate(Screen.Details.route) {
                        launchSingleTop = true
                    }
                }, onLanguageClick = {
                    navController.navigate(Screen.Language.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Screen.Language.route) {
            LanguageScreen(
                languageManager = languageManager,
                coroutineScope = coroutineScope,
                onBackClick = {
                    navController.popBackStack()
                },
                onLanguageSelected = {
                    context.startActivity(Intent(context, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                }
            )
        }


        composable(Screen.Details.route) {
            val item =
                navController.previousBackStackEntry?.savedStateHandle?.get<MovieDomainDetails>(
                    MOVIE_ITEM
                ) ?: return@composable

            DetailsScreen(
                item = item,
                onPlayClick = { url ->
                    navController.navigate(Screen.Play.route.replace("{$VIDEO_URL_KEY}", url)) {
                        launchSingleTop = true
                    }
                },
                onBackClick = { navController.popBackStack() })
        }

        composable(
            Screen.Play.route,
            arguments = listOf(navArgument(VIDEO_URL_KEY) { type = NavType.StringType })
        ) { backStackEntry ->
            val playScreenUrl = backStackEntry.arguments?.getString(VIDEO_URL_KEY)?.decodeUrl()
            playScreenUrl?.let {
                PlayerScreen(
                    videoUrl = it,
                    onBackClick = { navController.popBackStack() })
            }
        }
    }
}