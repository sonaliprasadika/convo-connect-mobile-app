package com.example.mobilecomputing

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Chat.route
        ){
            ConversationScreen(navController = navController)
        }
    }
}