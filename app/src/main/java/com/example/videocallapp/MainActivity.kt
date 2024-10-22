package com.example.videocallapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.videocallapp.connect.ConnectScreen
import com.example.videocallapp.connect.ConnectState
import com.example.videocallapp.connect.ConnectViewModel
import com.example.videocallapp.ui.theme.VideoCallAppTheme
import com.example.videocallapp.video.CallState
import com.example.videocallapp.video.VideoCallScreen
import com.example.videocallapp.video.VideoCallState
import io.getstream.video.android.compose.theme.VideoTheme
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoCallAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ConnectRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<ConnectRoute> {
                            val viewModel = koinViewModel<ConnectViewModel>()
                            val state = viewModel.state

                            LaunchedEffect(key1 = state.isConnected) {
                                if (state.isConnected)
                                    navController.navigate(VideoCallRoute) {
                                        popUpTo(ConnectRoute) {
                                            inclusive = true
                                        }
                                    }
                            }
                        }
                        ConnectScreen(state = state, onAction = viewModel::onAction) {

                        }
                    }
                    composable<VideoCallRoute> {
                        val viewModel = koinViewModel<ConnectViewModel>()
                        val state = viewModel.state

                        LaunchedEffect(key1 = state.callState) {
                            if (state.callState == CallState.ENDED)
                                navController.navigate(ConnectRoute) {
                                    popUpTo(VideoCallRoute) {
                                        inclusive = true

                                    }
                                }
                        }
                    }
                    VideoTheme {
                        VideoCallScreen(state = state, onAction = viewModel::onAction)
                    }
                }
            }
        }
    }
}


@Serializable
data object ConnectRoute

@Serializable
data object VideoCallRoute
