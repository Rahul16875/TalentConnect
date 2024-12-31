package com.example.talentconnect.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.talentconnect.FirebaseAuthHelper
import com.example.talentconnect.InfoCardWithIcon
import com.example.talentconnect.JobCardWithImage
import com.example.talentconnect.QualificationCard
import com.example.talentconnect.R

@Composable
fun RoleWelcomeScreen(navController: NavHostController, role: String, context: Context) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the $role dashboard!")
        Button(
            onClick = {
                FirebaseAuthHelper.logoutForRole(context, role) {
                    navController.navigate("initial") {
                        popUpTo("home/$role") { inclusive = true }
                    }
                }
            }
        ) {
            Text("Logout")
        }
    }
}

@Composable
fun CampusWelcomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF78BEFC),
                contentColor = Color.Black,
            ) {
                NavigationBarItem(selected = false, onClick = {navController.navigate("CollegeService")}, icon = {
                        Icon(imageVector = Icons.Default.Settings,
                            contentDescription = "Services",
                            tint = Color.Black)
                }, label = { Text("Services") })

                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(imageVector = Icons.Rounded.Send,
                        contentDescription = "Apply",
                        tint = Color.Black)
                }, label = { Text("Apply") })


                NavigationBarItem(selected = false, onClick = { navController.navigate("initial") }, icon = {
                    Icon(imageVector = Icons.Rounded.Home,
                        contentDescription = "Home",
                        tint = Color.Black)
                }, label = { Text("Home") })


                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(imageVector = Icons.Rounded.Person,
                        contentDescription = "Person",
                        tint = Color.Black)
                }, label = { Text("Person") })


            }
        }
    ) {it ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Top Row: College Profile & Companies Applied
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
//                        .padding(top = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(1) {
                        InfoCardWithIcon(
                            icon = Icons.Rounded.Person,
                            title = "College's Profile",
                            subtitle = "Updated 90d ago",
                        ) {}
                    }

                    items(1) {
                        InfoCardWithIcon(
                            icon = Icons.Rounded.Info,
                            title = "Company's Applied",
                            subtitle = "Last 9d ago",
                        ) {}
                    }
                }
            }

            item {

                SectionHeader("Companies Posted Jobs") { /* Handle View All */ }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(1) { // Example: Display 2 Job Cards
                        JobCardWithImage(
                            imageResId = R.drawable.building,
                            jobTitle = "Marketing" ,
                            companyName = "XYZ",
                            location = "Remote",
                            timeAgo = "6h Ago",
                            modifier = Modifier.width(140.dp)
                        )
                    }

                    items(1) { // Example: Display 2 Job Cards
                        JobCardWithImage(
                            imageResId = R.drawable.building,
                            jobTitle = "Software Developer",
                            companyName = "XYZ",
                            location = "Remote",
                            timeAgo = "6h Ago",
//                        modifier = Modifier.width(150.dp)
                        )
                    }
                }
            }

            item {

                Row (modifier = Modifier.padding(top = 10.dp, bottom = 15.dp)){
                    Text(text = "Companies Posting For OnCampus", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(end = 15.dp))
                    Text(text = "View All", fontWeight = FontWeight.SemiBold, color = Color.Blue)
                }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(3) { // Example: Display 3 Qualification Cards
                        QualificationCard(
                            imageResId = R.drawable.building,
                            title = when (it) {
                                0 -> "B.TECH/BE"
                                1 -> "DIPLOMA"
                                else -> "MBA"
                            },
                            modifier = Modifier.width(120.dp)
                        )
                    }
                }
            }

            item {

                Row (modifier = Modifier.padding(top = 10.dp, bottom = 15.dp)){
                    Text(text = "Companies Posting For Internship", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(end = 15.dp))
                    Text(text = "View All", fontWeight = FontWeight.SemiBold, color = Color.Blue)
                }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(3) { // Example: Display 3 Qualification Cards
                        QualificationCard(
                            imageResId = R.drawable.building,
                            title = when (it) {
                                0 -> "B.TECH/BE"
                                1 -> "DIPLOMA"
                                else -> "MBA"
                            },
                            modifier = Modifier.width(120.dp)
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun SectionHeader(title: String, onViewAllClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
//            .padding(top = 1.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = "View All",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onViewAllClick() }
        )
    }
}

@Composable
fun CompanyWelcomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF78BEFC),
                contentColor = Color.Black,
            ) {
                NavigationBarItem(selected = false, onClick = {
                    navController.navigate("companyService")
                },
                    icon = {
                    Icon(imageVector = Icons.Default.Settings,
                        contentDescription = "Services",
                        tint = Color.Black)
                }, label = { Text("Services") })

                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(imageVector = Icons.Rounded.Send,
                        contentDescription = "Apply",
                        tint = Color.Black)
                }, label = { Text("Apply") })


                NavigationBarItem(selected = false, onClick = { navController.navigate("initial") }, icon = {
                    Icon(imageVector = Icons.Rounded.Home,
                        contentDescription = "Home",
                        tint = Color.Black)
                }, label = { Text("Home") })


                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(imageVector = Icons.Rounded.Person,
                        contentDescription = "Person",
                        tint = Color.Black)
                }, label = { Text("Person") })


            }
        }
    ) {it ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Top Row: College Profile & Companies Applied
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
//                        .padding(top = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(1) {
                        InfoCardWithIcon(
                            icon = Icons.Rounded.Person,
                            title = "Company's Profile",
                            subtitle = "Updated 90d ago",
                        ) {}
                    }

                    items(1) {
                        InfoCardWithIcon(
                            icon = Icons.Rounded.Info,
                            title = "Job's Posted",
                            subtitle = "Last 9d ago",
                        ) {}
                    }
                }
            }

            item {

                SectionHeader("Posted Jobs") { /* Handle View All */ }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(1) { // Example: Display 2 Job Cards
                        JobCardWithImage(
                            imageResId = R.drawable.building,
                            jobTitle = "Marketing" ,
                            companyName = "XYZ",
                            location = "Remote",
                            timeAgo = "6h Ago",
                            modifier = Modifier.width(140.dp)
                        )
                    }

                    items(1) { // Example: Display 2 Job Cards
                        JobCardWithImage(
                            imageResId = R.drawable.building,
                            jobTitle = "Software Developer",
                            companyName = "XYZ",
                            location = "Remote",
                            timeAgo = "6h Ago",
//                        modifier = Modifier.width(150.dp)
                        )
                    }
                }
            }

            item {

                Row (modifier = Modifier.padding(top = 10.dp, bottom = 15.dp)){
                    Text(text = "Colleges Posting For OnCampus", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(end = 15.dp))
                    Text(text = "View All", fontWeight = FontWeight.SemiBold, color = Color.Blue)
                }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(3) { // Example: Display 3 Qualification Cards
                        QualificationCard(
                            imageResId = R.drawable.building,
                            title = when (it) {
                                0 -> "B.TECH/BE"
                                1 -> "DIPLOMA"
                                else -> "MBA"
                            },
                            modifier = Modifier.width(120.dp)
                        )
                    }
                }
            }

            item {

                Row (modifier = Modifier.padding(top = 10.dp, bottom = 15.dp)){
                    Text(text = "Colleges Posting For Internship", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(end = 15.dp))
                    Text(text = "View All", fontWeight = FontWeight.SemiBold, color = Color.Blue)
                }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(3) { // Example: Display 3 Qualification Cards
                        QualificationCard(
                            imageResId = R.drawable.building,
                            title = when (it) {
                                0 -> "B.TECH/BE"
                                1 -> "DIPLOMA"
                                else -> "MBA"
                            },
                            modifier = Modifier.width(120.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun StudentWelcomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF78BEFC),
                contentColor = Color.Black,
            ) {
                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(imageVector = Icons.Default.Settings,
                        contentDescription = "Services",
                        tint = Color.Black)
                }, label = { Text("Services") })

                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(imageVector = Icons.Rounded.Send,
                        contentDescription = "Apply",
                        tint = Color.Black)
                }, label = { Text("Apply") })


                NavigationBarItem(selected = false, onClick = { navController.navigate("initial") }, icon = {
                    Icon(imageVector = Icons.Rounded.Home,
                        contentDescription = "Home",
                        tint = Color.Black)
                }, label = { Text("Home") })


                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(imageVector = Icons.Rounded.Person,
                        contentDescription = "Person",
                        tint = Color.Black)
                }, label = { Text("Person") })


            }
        }
    ) {it ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Top Row: College Profile & Companies Applied
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
//                        .padding(top = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(1) {
                        InfoCardWithIcon(
                            icon = Icons.Rounded.Person,
                            title = "Student's Profile",
                            subtitle = "Updated 90d ago",
                        ) {}
                    }

                    items(1) {
                        InfoCardWithIcon(
                            icon = Icons.Rounded.Info,
                            title = "Job's Applied",
                            subtitle = "Last 9d ago",
                        ) {}
                    }
                }
            }

            item {

                SectionHeader("Jobs Based on Your Profile") { /* Handle View All */ }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(1) { // Example: Display 2 Job Cards
                        JobCardWithImage(
                            imageResId = R.drawable.building,
                            jobTitle = "Marketing" ,
                            companyName = "XYZ",
                            location = "Remote",
                            timeAgo = "6h Ago",
                            modifier = Modifier.width(140.dp)
                        )
                    }

                    items(1) { // Example: Display 2 Job Cards
                        JobCardWithImage(
                            imageResId = R.drawable.building,
                            jobTitle = "Software Developer",
                            companyName = "XYZ",
                            location = "Remote",
                            timeAgo = "6h Ago",
//                        modifier = Modifier.width(150.dp)
                        )
                    }
                }
            }


            item {

                SectionHeader("Jobs Based on Your Preferences") { /* Handle View All */ }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(1) { // Example: Display 2 Job Cards
                        JobCardWithImage(
                            imageResId = R.drawable.building,
                            jobTitle = "Marketing" ,
                            companyName = "XYZ",
                            location = "Remote",
                            timeAgo = "6h Ago",
                            modifier = Modifier.width(140.dp)
                        )
                    }

                    items(1) { // Example: Display 2 Job Cards
                        JobCardWithImage(
                            imageResId = R.drawable.building,
                            jobTitle = "Software Developer",
                            companyName = "XYZ",
                            location = "Remote",
                            timeAgo = "6h Ago",
//                        modifier = Modifier.width(150.dp)
                        )
                    }
                }
            }

        }
    }
}

data class TopLevelRoute(
    val name: String,
    val route: String,
    val icon: ImageVector
)