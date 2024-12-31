package com.example.talentconnect.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.talentconnect.FirebaseAuthHelper

@Composable
fun LoginScreen(navController: NavHostController, role: String, context: Context) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Connecting Campuses with Talent.")
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Welcome, Campus Partner")
        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(onClick = {
            // Perform Firebase login
            FirebaseAuthHelper.loginUser(email.value, password.value) { success, returnedRole, error ->
                if (success) {
                    if (returnedRole == role) {
                        // Mark the user as logged in for the role
                        FirebaseAuthHelper.setUserLoggedInForRole(context, role, true)
                        navController.navigate("home/$role") {
                            popUpTo("login/$role") { inclusive = true }
                        }
                    } else {
                        errorMessage.value = "Invalid login. Please select the correct role."
                    }
                } else {
                    errorMessage.value = error ?: "Login failed. Please try again."
                }
            }
        }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Error Message
        Text(text = errorMessage.value, color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Button to navigate to the Sign Up screen for the selected role
        Button(onClick = {
            navController.navigate("signup/$role")
        }) {
            Text("Don't have an account? Sign Up")
        }
    }
}
