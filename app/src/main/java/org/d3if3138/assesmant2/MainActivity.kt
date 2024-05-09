package org.d3if3138.assesmant2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import org.d3if3138.assesmant2.data.Diarydatabase
import org.d3if3138.assesmant2.theme.Assesmant2Theme
import org.d3if3138.assesmant2.ui.AddDiaryScreen
import org.d3if3138.assesmant2.ui.DiaryScreen


class MainActivity : ComponentActivity() {

    private val database by lazy { Room.databaseBuilder(
        applicationContext,
        Diarydatabase::class.java,
        "diary.db"
    ).build()
    }

    private val viewModel by viewModels<DiaryViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DiaryViewModel(database.dao) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assesmant2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModel.state.collectAsState()
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController, startDestination = "DiaryScreen") {
                        composable("DiaryScreen") {
                            DiaryScreen(
                                state = state,
                                navController = navController,
                                onEvent = viewModel::onEvent
                            )
                        }
                        composable("AddDiaryScreen") {
                            AddDiaryScreen(
                                state = state,
                                navController = navController,
                                onEvent = viewModel::onEvent)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assesmant2Theme {

    }
}