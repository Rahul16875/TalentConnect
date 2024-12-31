package com.example.talentconnect.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.talentconnect.FirebaseAuthHelper

@Composable
fun SignupScreen(navController: NavHostController, role: String, context: Context) {
    val college = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val contact = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Campus Representative SignUp", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Connect your institution with a world of opportunities",
            fontSize = 14.sp,fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(15.dp))

        TextField(value = college.value, onValueChange = { email.value = it }, label = { Text("Enter Your College Name") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = email.value, onValueChange = { email.value = it }, label = { Text("Your College Email Address") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = contact.value, onValueChange = { email.value = it }, label = { Text("Enter a Contact Number") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = password.value, onValueChange = { password.value = it }, label = { Text("Your Password") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = confirmPassword.value, onValueChange = { confirmPassword.value = it }, label = { Text("Confirm Password") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (password.value != confirmPassword.value) {
                errorMessage.value = "Passwords do not match!"
            } else {
                // Proceed with registration
                FirebaseAuthHelper.registerUser(college.value,email.value,contact.value, password.value, role) { success, error ->
                    if (success) {
                        // Mark the user as logged in for this role
                        FirebaseAuthHelper.setUserLoggedInForRole(context, role, true)

                        // Navigate directly to the Welcome Screen for the selected role
                        // Pop the entire back stack to avoid returning to the signup page
                        navController.navigate("home/$role") {
                            popUpTo("initial") { inclusive = true }  // Clears the back stack back to initial screen
                        }
                    } else {
                        errorMessage.value = error ?: "Signup failed."
                    }
                }
            }
        }) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(text = "Already have an account?")
            Text(text = "Login here.", color = Color.Blue,
                fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable {
                    navController.navigate("login/{role}")
                })
        }
        Text(errorMessage.value)
    }
}

