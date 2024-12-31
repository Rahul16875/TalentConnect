package com.example.talentconnect.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.talentconnect.InfoCardWithIcon
import com.example.talentconnect.JobCardWithImage
import com.example.talentconnect.QualificationCard
import com.example.talentconnect.R

@Composable
fun CompanyServiceScreen(navController: NavHostController) {
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

                Spacer(modifier = Modifier.height(30.dp))

                Text(text = "Services Provided By Talent Connect", fontWeight = FontWeight.Bold)

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(3) { // Example: Display 3 Qualification Cards
                        QualificationCard(
                            imageResId = R.drawable.building,
                            title = when (it) {
                                0 -> "Campus Drives"
                                1 -> "Staffing \nSolutions"
                                else -> "Internship \nPrograms"
                            },
                            modifier = Modifier.width(120.dp)
                        )
                    }
                }
            }

            item {

                Spacer(modifier = Modifier.height(40.dp))

//                Text(text = "Services Provided By Talent Connect", fontWeight = FontWeight.Bold)

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(3) { // Example: Display 3 Qualification Cards
                        QualificationCard(
                            imageResId = R.drawable.building,
                            title = when (it) {
                                0 -> "Part of Job Fairs"
                                1 -> "Staff \nTraining Programs"
                                else -> "Employer \nBranding"
                            },
                            modifier = Modifier.width(120.dp)
                        )
                    }
                }
            }

        }
    }
}