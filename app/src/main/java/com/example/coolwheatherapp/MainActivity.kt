package com.example.coolwheatherapp

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
 import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
 import androidx.compose.foundation.Image
import androidx.compose.foundation.background
 import androidx.compose.foundation.layout.*
 import androidx.compose.foundation.lazy.LazyRow
 import androidx.compose.foundation.lazy.itemsIndexed
 import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
 import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.coolwheatherapp.data.model.Weather.Hour
import com.example.coolwheatherapp.ui.home.HomeViewModel
import com.example.coolwheatherapp.ui.theme.CoolWheatherAPPTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
    val weatherForcast by homeViewModel.forcastweatherResp.observeAsState()

    val quote by homeViewModel.quoteResp.observeAsState()
        toDaysQUOTE= quote?.quote.toString() +"\n"+ quote?.author.toString()
    weatherDegree = weather?.current?.tempC.toString()
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


            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://"+weather?.current?.condition?.icon.toString().drop(2))
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sunnyvector),
                contentDescription = "Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .align(CenterVertically)
                    .padding(10.dp)     
            ,
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
            Column {
LazyRow{
    if (weatherForcast?.equals(null) == true){
        item {  CircularProgressIndicator(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Center))

        }

    }
else{
        val hourlist= weatherForcast?.forecast?.forecastday?.get(0)?.hour
if (hourlist!=null){
        itemsIndexed(items = hourlist!!){index, item ->
    WeatherForcastItem(hour = item)
        }
}
    }

}

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
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .padding(top = 10.dp),
            text = toDaysQUOTE,

            fontSize = 18.sp,

            color =  colorResource(id = R.color.white2),
            textAlign = TextAlign.Start

        )
      }
    }

@Composable
fun WeatherForcastItem(hour: Hour){
    Column {
        Row {

            Text(
                modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                text = hour.time.drop(10),
                 fontSize = 25.sp,
                fontWeight = FontWeight.Bold,

                color =  colorResource(id = R.color.white),
                textAlign = TextAlign.Center

            )
        }
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://"+hour?.condition?.icon.toString().drop(2))
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sunnyvector),
                contentDescription = "Icon",
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .size(60.dp)
                    .align(CenterVertically)
                    .padding(start = 0.dp)
            ,
                colorFilter = ColorFilter.tint(Color.White)
                                )
            Text(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .align(CenterVertically),
                text = hour.tempC.toString()+"°",
                 fontSize = 20.sp,

                color =  colorResource(id = R.color.white),
                textAlign = TextAlign.Center

            )
        }

    }
    Column {
        Divider(
            color = Color.White,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp))
    }
}

//Request Location Permission


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoolWheatherAPPTheme {

      }
}