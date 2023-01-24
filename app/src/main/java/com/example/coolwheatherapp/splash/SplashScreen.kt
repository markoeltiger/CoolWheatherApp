package com.example.coolwheatherapp.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.glance.Image
import com.example.coolwheatherapp.Greeting
import com.example.coolwheatherapp.QuotePart
import com.example.coolwheatherapp.R
import com.example.coolwheatherapp.SwipeRefreshCompose
import com.example.coolwheatherapp.ui.theme.CoolWheatherAPPTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : ComponentActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            CoolWheatherAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SplashScreenLogo()
                }



            }
        }
    }
}
@Composable
fun SplashScreenLogo (){
    Column(modifier = Modifier.fillMaxSize(),Arrangement.Center) {


        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.coollogo),
            contentDescription = "Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
    }
}