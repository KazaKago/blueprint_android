package com.kazakago.blueprint.ui.global.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kazakago.blueprint.ui.feature.about.About
import com.kazakago.blueprint.ui.feature.about.AboutScreen
import com.kazakago.blueprint.ui.feature.todo.ToDoList
import com.kazakago.blueprint.ui.feature.todo.ToDoListScreen

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Any = ToDoList,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<ToDoList> {
            ToDoListScreen(
                onNavigateAbout = {
                    navController.navigate(About)
                },
            )
        }
        composable<About> {
            AboutScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
            )
        }
    }
}
