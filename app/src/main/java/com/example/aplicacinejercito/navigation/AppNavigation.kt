package com.example.aplicacinejercito.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicacinejercito.ui.theme.screen.LoginScreen
import com.example.aplicacinejercito.ui.theme.screen.CadeteScreen
import com.example.aplicacinejercito.ui.theme.screen.AdminScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        // Pantalla de Login
        composable("login") {
            LoginScreen(
                onLoginSuccess = { esAdmin ->
                    if (esAdmin) {
                        navController.navigate("admin") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        navController.navigate("cadete") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }
            )
        }

        // Pantalla de Cadete
        composable("cadete") {
            CadeteScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("cadete") { inclusive = true }
                    }
                }
            )
        }

        // Pantalla de Administrador
        composable("admin") {
            AdminScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("admin") { inclusive = true }
                    }
                }
            )
        }
    }
}