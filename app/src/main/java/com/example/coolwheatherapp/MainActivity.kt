package com.example.coolwheatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
 import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coolwheatherapp.ui.home.homeViewModel
import com.example.coolwheatherapp.ui.theme.CoolWheatherAPPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            CoolWheatherAPPTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(
    homeViewModel: homeViewModel = viewModel()
) {
    var weatherDegree by remember { mutableStateOf("22") }
    val Weather by homeViewModel.weatherResp.observeAsState()

    weatherDegree= Weather?.current?.tempC.toString()
Log.d("CurrentWeather",weatherDegree.toString())
    Image(
        painter = painterResource(id = R.drawable.sunnybackground),
        contentDescription ="test",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )

    Column (Modifier.fillMaxSize()){
Box(  modifier = Modifier

    .align(CenterHorizontally)
    .padding(top = 50.dp)
    .height(350.dp)
    .width(300.dp)
    .clip(shape = RoundedCornerShape(size = 20.dp))

    .background(colorResource(id = R.color.SunnyYellow))
            ,    contentAlignment = Center
) {
    Column(modifier = Modifier.align(TopCenter)) {
        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(15.dp)) {
            Text(  text = "Today",
                color =  colorResource(id = R.color.SunnyTextYellow),

                textAlign = TextAlign.Center

            )
            Image( 
                painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                contentDescription = "Down",
                contentScale = ContentScale.Fit,
                modifier = Modifier
            )
        }

        Row(modifier = Modifier.align(CenterHorizontally),Arrangement.Center) {
            Image(
                painter = painterResource(id = R.drawable.sunnyvector),
                contentDescription = "Down",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(70.dp)
                    .align(CenterVertically)
                    .padding(10.dp)
            )
            Text(  text = "${Weather?.current?.tempC.toString()}°",
                fontSize = 70.sp,
               color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center
            )

        }
        Row(modifier = Modifier.align(CenterHorizontally),Arrangement.Center) {

            Text(  text = "${Weather?.current?.condition?.text}",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center
            )

        }
        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 20.dp),Arrangement.Center) {

            Text(  text = "${Weather?.location?.region}",
                fontSize = 15.sp,
                color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center

            )

        }
        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 20.dp),Arrangement.Center) {

            Text(  text = "${Weather?.location?.localtime}",
                fontSize = 15.sp,
                color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center
            )

        }
        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 20.dp),Arrangement.Center) {

            Text(  text = "Feels like ${Weather?.current?.feelslikeC}",
                fontSize = 15.sp,
                color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center
            ,
              modifier=  Modifier.padding(end = 10.dp)
            )
            Text(  text = "|",
                fontSize = 15.sp,
                color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center
            )
            Text(  text = "Humidity ${Weather?.current?.humidity}",
                fontSize = 15.sp,
                color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center, modifier = Modifier.padding(start = 10.dp)
            )

        }



    }


            }
        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 20.dp))
            .blur(100.dp)
            .padding(top = 40.dp)
            .background(colorResource(id = R.color.SunnyYellow).copy(alpha = 0.8f))
            .height(120.dp)
            .width(300.dp)
            .align(CenterHorizontally)
            .clip(shape = RoundedCornerShape(size = 20.dp))
            ,    contentAlignment = Center ) {
            Column() {


            }

        }
        Text(
            modifier = Modifier.padding(start = 50.dp, top = 40.dp),
            text = "Today`s Quote",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,

            color =  colorResource(id = R.color.white2),
            textAlign = TextAlign.Center

        )

        Text(
            modifier = Modifier.padding(horizontal = 50.dp),
            text = "Improve him believe opinion offered met and " +
                    "end cheered forbade. Friendly as stronger " +
                    "speedily by recurred. Son interest wandered " +
                    "sir addition end say. Manners beloved affixed " +
                    "picture men ask.",

            fontSize = 17.sp,

            color =  colorResource(id = R.color.white2),
            textAlign = TextAlign.Start

        )
      }
    }




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoolWheatherAPPTheme {
        Greeting()
    }
}