package com.example.talentconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoCardWithIcon(
    icon: ImageVector,
    title: String,
    subtitle: String,
    backgroundColor: Color = Color.LightGray,
    iconBackgroundColor: Color = Color.White,
    modifier: Modifier = Modifier,
    onViewAllClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = iconBackgroundColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "View Profile",
                    fontSize = 12.sp,
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable { onViewAllClick() }
                )
            }
        }
    }
}

@Composable
fun JobCardWithImage(
    imageResId: Int,
    jobTitle: String,
    companyName: String,
    location: String,
    timeAgo: String,
    backgroundColor: Color = Color.LightGray,
    imageBackgroundColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = imageBackgroundColor, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = jobTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = companyName,
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                Row {
                    Icon(imageVector = Icons.Rounded.LocationOn, contentDescription ="" )
                    Text(
                        text = "$location",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
                Text(
                    text = "$timeAgo",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

        }
    }
}

@Composable
fun QualificationCard(
    imageResId: Int,
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.LightGray,
    iconBackgroundColor: Color = Color.White
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(color = iconBackgroundColor, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

//@Composable
//fun BottomNavigationBar() {
//    BottomAppBar(
//        containerColor = Color(0xFF78BEFC),
//        contentColor = Color.Black,
////        elevation = 8.dp
//    ) {
//        BottomNavigationItem(
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Settings, // Use your icon here
//                    contentDescription = "Services",
//                    tint = Color.Black
//                )
//            },
//            label = { Text("Services") },
//            selected = false,
//            onClick = { /* Handle Services click */ }
//        )
//
//        BottomNavigationItem(
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Send, // Use your icon here
//                    contentDescription = "Apply",
//                    tint = Color.Black
//                )
//            },
//            label = { Text("Apply") },
//            selected = false,
//            onClick = { /* Handle Apply click */ }
//        )
//
//        BottomNavigationItem(
//            icon = {
//                Box(
//                    modifier = Modifier
//                        .size(50.dp)
//                        .background(color = Color(0xFF81D4FA), shape = CircleShape),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Home, // Use your icon here
//                        contentDescription = "Home",
//                        tint = Color.Black
//                    )
//                }
//            },
//            label = { Text("Home") },
//            selected = true, // Highlight the Home item
//            onClick = { /* Handle Home click */ }
//        )
//
//        BottomNavigationItem(
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Person, // Use your icon here
//                    contentDescription = "Profile",
//                    tint = Color.Black
//                )
//            },
//            label = { Text("Profile") },
//            selected = false,
//            onClick = { /* Handle Profile click */ }
//        )
//    }
//}
