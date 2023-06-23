package com.example.aularoomdatabase2023

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aularoomdatabase2023.screen.*
import com.example.aularoomdatabase2023.ui.theme.AulaRoomDatabaseTheme
import com.example.aularoomdatabase2023.viewModel.DeleteTravelViewModel
import com.example.aularoomdatabase2023.viewModel.NewTravelViewModelFactory
import com.example.aularoomdatabase2023.viewModel.RegisterNewTravelViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AulaRoomDatabaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar() {
                Button(onClick = { navController.navigate("login") }) {
                    Text(text = "Login")
                }
                Button(onClick = { navController.navigate("form") }) {
                    Text(text = "Novo Usuário")
                }
                Button(onClick = { navController.navigate("about") }) {
                    Text(text = "Sobre")
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(paddingValues = it)) {
            NavHost(
                navController = navController,
                startDestination = "login" ) {

                composable("login") {
                    LoginScreen(
                        onAfterLogin = { userId ->
                            navController.navigate("list_travel/$userId")
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Login ok"
                                )
                            }
                        }
                    )
                }

                composable("form") {
                    FormScreen(
                        onAfterSave = {
                            navController.navigateUp()
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "User registered"
                                )
                            }
                        }
                    )
                }

                composable("about") {
                    AboutScreen()
                }

                composable(
                    "list_travel/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                ) {
                    val param = it.arguments?.getString("userId")
                    val userId = param?.toInt()
                    if (userId != null) {
                        ListScreen(
                            userId,
                            OpenNewTravel = { userId ->
                                navController.navigate("new_travel/$userId")
                            },
                            excludeTravel = { userId ->
                                navController.navigate("exclude_travel/$userId")
                            },
                            listExpenses = {travelId ->
                                navController.navigate("list_expense/$travelId")
                            }
                        )
                    }
                }

                composable(
                    "new_travel/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                ) {
                    val param = it.arguments?.getString("userId")
                    val userId = param?.toInt()
                    if (userId != null) {
                        NewTravel(
                            userId,
                            onBack = {
                                navController.navigateUp()
                            }
                        )
                    }
                }

                composable(
                    "exclude_travel/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                ){
                    val param = it.arguments?.getString("userId")
                    val userId = param?.toInt()
                    if (userId != null) {
                        DeleteTravelScreen(
                            userId
                        )
                    }
                }

                composable(
                    "list_expense/{travelId}",
                    arguments = listOf(navArgument("travelId") { type = NavType.StringType })
                ){
                    val param = it.arguments?.getString("travelId")
                    val travelId = param?.toInt()
                    if (travelId != null) {
                        ExpenseListScreen(
                            travelId,
                            OpenNewExpense = {travelId ->
                                navController.navigate("new_expense/$travelId")
                            }
                        )
                    }
                }

                composable(
                    "new_expense/{travelId}",
                    arguments = listOf(navArgument("travelId") { type = NavType.StringType })
                ) {
                    val param = it.arguments?.getString("travelId")
                    val travelId = param?.toInt()
                    if (travelId != null) {
                        NewExpense(
                            travelId,
                            onBack = {
                                navController.navigateUp()
                            }
                        )
                    }
                }

                composable(
                    "exclude_expense/{travelId}",
                    arguments = listOf(navArgument("travelId") { type = NavType.StringType })
                ){

                }
            }
        }
    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AulaRoomDatabaseTheme {
       MyApp()
    }
}