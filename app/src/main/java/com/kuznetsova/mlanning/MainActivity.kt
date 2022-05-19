package com.kuznetsova.mlanning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kuznetsova.mlanning.ui.AddTaskScreen
import com.kuznetsova.mlanning.ui.MainScreen
import com.kuznetsova.mlanning.ui.TaskScreen
import com.kuznetsova.mlanning.ui.theme.MlanningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            val navController = rememberNavController()

            MlanningTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(id = R.string.app_name))
                            },
                            backgroundColor = Color(0xffff6f00),
                            contentColor = Color.Black,
                            elevation = 12.dp,
                        )
                    },
                    content = { /*TaskScreen(
                        Task(
                        1, "Studying", "Ghtdf dsfjh dfh dfhd",
                        listOf(
                            TaskItem(1, "2222222 22222", Day(21, 10, 2025), true),
                            TaskItem(1, "2222222 22222", Day(21, 10, 2022), false),
                            TaskItem(1, "2222222 22222", Day(21, 10, 2021), true)
                        ), TaskPriority.LOW
                    )
                    )}*///AddTaskScreen()}//
                        NavHost(navController = navController, startDestination = "main") {
                            composable("main") { MainScreen(navController = navController) }
                            composable(
                                "detailScreen/{taskId}",
                                arguments = listOf(
                                    navArgument("taskId") { type = NavType.LongType }
                                )
                            ) { backStackEntry ->
                                TaskScreen(
                                    navController,
                                    backStackEntry.arguments!!.getLong("taskId")
                                )
                            }
                            composable("AddTaskScreen") { AddTaskScreen(navController = navController) }

                        }

                    }
                )


            }
        }
    }
}

@Composable
fun Toolbar(name: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {


        Text(text = "Hel77o $name!")
        Text(text = "Hello $name!")
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MlanningTheme {
        Toolbar("Android")
    }
}