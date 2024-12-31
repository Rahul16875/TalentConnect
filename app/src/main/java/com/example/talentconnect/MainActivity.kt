package com.example.talentconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.talentconnect.screens.CampusWelcomeScreen
import com.example.talentconnect.screens.CollegeServiceScreen
import com.example.talentconnect.screens.CompanyServiceScreen
import com.example.talentconnect.screens.CompanyWelcomeScreen
import com.example.talentconnect.screens.InitialScreen
import com.example.talentconnect.screens.LoginScreen
//import com.example.talentconnect.screens.LoginScreen
import com.example.talentconnect.screens.RoleWelcomeScreen
import com.example.talentconnect.screens.SignupScreen
import com.example.talentconnect.screens.StudentWelcomeScreen
import com.example.talentconnect.ui.theme.TalentConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TalentConnectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TalentConnectApp()
                }
            }
        }
    }
}

@Composable
fun TalentConnectApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "initial") {
        composable("initial") { InitialScreen(navController) }
        composable("login/{role}") { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role") ?: "student"
            LoginScreen(navController, role, LocalContext.current)
        }
        composable("signup/{role}") { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role") ?: "student"
            SignupScreen(navController, role, LocalContext.current)
        }
        composable("home/{role}") { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role") ?: "student"
            RoleWelcomeScreen(navController, role, LocalContext.current)
        }

        // Welcome screens for different roles
        composable("home/campus") { CampusWelcomeScreen(navController = navController) }
        composable("home/company") { CompanyWelcomeScreen(navController = navController) }
        composable("home/student") { StudentWelcomeScreen(navController = navController) }
        composable("companyService") { CompanyServiceScreen(navController) }
        composable("collegeService") { CollegeServiceScreen(navController) }



    }
}




