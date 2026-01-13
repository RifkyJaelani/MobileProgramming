package com.example.myapplication1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.GoogleAuthUIClient
import com.example.myapplication1.ui.theme.MyApplication1Theme
import com.example.presentation.profile.ProfileScreen
import com.example.presentation.sign_in.SignInScreen
import com.example.presentation.sign_in.SignInViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val googleAuthUIClient by lazy {
        GoogleAuthUIClient(
            context = this
        )
    }

    @SuppressLint("ComposableDestinationInComposeScope")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication1Theme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "sign_in")
                    {
                        composable("sign_in") {
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                if (googleAuthUIClient.getSignInUser() != null) {
                                    navController.navigate("Profile")
                                }
                            }

                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if (state.isSignInSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign In Successful",
                                        Toast.LENGTH_LONG

                                    ).show()

                                    navController.navigate("Profile")
                                    viewModel.resetState()
                                }
                            }
                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val result = googleAuthUIClient.signIn()
                                        viewModel.onSignInResult(result)

                                    }
                                }
                            )
                        }
                        composable("Profile") {
                            ProfileScreen(
                                userData = googleAuthUIClient.getSignInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUIClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Signet Out",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.popBackStack()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

