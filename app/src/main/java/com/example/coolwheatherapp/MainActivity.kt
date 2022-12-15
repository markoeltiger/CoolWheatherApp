package com.example.coolwheatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
 import androidx.compose.foundation.Image
import androidx.compose.foundation.background
 import androidx.compose.foundation.layout.*
 import androidx.compose.foundation.shape.RoundedCornerShape
 import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
 import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
 import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
 import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coolwheatherapp.ui.home.HomeViewModel
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
    homeViewModel: HomeViewModel = viewModel()
) {
    var toDaysQUOTE by remember { mutableStateOf("Loading") }

    var weatherDegree by remember { mutableStateOf("22") }
    val weather by homeViewModel.weatherResp.observeAsState()
    val quote by homeViewModel.quoteResp.observeAsState()
        toDaysQUOTE= quote?.quote.toString() +"\n"+ quote?.author.toString()
    weatherDegree = weather?.current?.tempC.toString()
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
            Text(  text = "${weather?.current?.tempC.toString()}°",
                fontSize = 70.sp,
               color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center
            )

        }
        Row(modifier = Modifier.align(CenterHorizontally),Arrangement.Center) {

            Text(  text = "${weather?.current?.condition?.text}",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center
            )

        }
        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 20.dp),Arrangement.Center) {

            Text(  text = "${weather?.location?.region}",
                fontSize = 15.sp,
                color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center

            )

        }
        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 20.dp),Arrangement.Center) {

            Text(  text = "${weather?.location?.localtime}",
                fontSize = 15.sp,
                color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center
            )

        }
        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 20.dp),Arrangement.Center) {

            Text(  text = "Feels like ${weather?.current?.feelslikeC}",
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
            Text(  text = "Humidity ${weather?.current?.humidity}",
                fontSize = 15.sp,
                color =  colorResource(id = R.color.SunnyTextYellow),
                textAlign = TextAlign.Center, modifier = Modifier.padding(start = 10.dp)
            )

        }



    }


            }
        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 20.dp))
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
            modifier = Modifier.padding(horizontal = 50.dp).padding(top = 10.dp),
            text = toDaysQUOTE,

            fontSize = 18.sp,

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