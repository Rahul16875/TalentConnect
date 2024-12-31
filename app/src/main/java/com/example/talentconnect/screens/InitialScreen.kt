package com.example.talentconnect.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.talentconnect.FirebaseAuthHelper
import com.example.talentconnect.R

@Composable
fun InitialScreen(navController: NavHostController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Welcome To",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )

        // Image
        Image(
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = "welcome",
            modifier = Modifier.size(300.dp).padding(start = 80.dp)
        )

        Text(
            text = "TalentConnect",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tagline
        Text(
            text = "Empowering Talent, Connecting Opportunities.",
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Role Selection Prompt
        Text(text = "Select Your Role to Get Started")
        Spacer(modifier = Modifier.height(12.dp))

        // Role Buttons
        Button(onClick = { handleRoleSelection("campus", navController, context) }) {
            Text("Campus")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { handleRoleSelection("company", navController, context) }) {
            Text("Company")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { handleRoleSelection("student", navController, context) }) {
            Text("Student")
        }
    }
}

fun handleRoleSelection(role: String, navController: NavHostController, context: Context) {
    val isLoggedIn = FirebaseAuthHelper.isUserLoggedInForRole(context, role)
    if (isLoggedIn) {
        navController.navigate("home/$role")
    } else {
        navController.navigate("login/$role")
    }
}
