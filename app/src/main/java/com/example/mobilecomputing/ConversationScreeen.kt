package com.example.mobilecomputing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ConversationScreen(
    navController: NavController
){
    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .size(40.dp)
                .padding(2.dp),
            text = "User"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(R.drawable.snow_man),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .padding(4.dp)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Text(
            modifier = Modifier
            .clickable {
                navController.navigate(route = Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            }
            .size(70.dp)
            .padding(8.dp) // Add padding as needed
            .background(color = Color.Gray) // Optional: Add background color
            .border(1.dp, Color.Black) // Optional: Add border
            .padding(8.dp),
            text = "Back"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ConversationScreenPreview(){
    ConversationScreen(
        navController = rememberNavController()
    )
}