package com.example.coolwheatherapp

import com.example.coolwheatherapp.R



import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
 import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.coolwheatherapp.data.model.Weather.Hour
import com.example.coolwheatherapp.data.model.Weather.Location
import com.example.coolwheatherapp.ui.home.HomeViewModel
import com.example.coolwheatherapp.ui.theme.CoolWheatherAPPTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.lang.Float.max
import java.lang.Float.min
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            CoolWheatherAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SwipeRefreshCompose()
                      Greeting()
                      QuotePart()
                  }


            }
        }
    }
}

@Composable
fun SwipeRefreshCompose() {

    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(3000)
            refreshing = false
        }
    }

    SwipeRefresh(

        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        modifier = Modifier.fillMaxSize(),
        onRefresh = { refreshing = true },
    ) {

        // list view

    }

}
@Composable
fun raindroplayout(){
    var  modifier=Modifier.size(500.dp)
    Layout(
    modifier = modifier.rotate(30f), //raindrop rotation angle

    content = { // child composable
        rainDrop(modifier.fillMaxSize())
        rainDrop(modifier.fillMaxSize())
        rainDrop(modifier.fillMaxSize())
    }
) { measurables, constraints ->
    // List of measured children
    val placeables = measurables.mapIndexed { index, measurable ->
        // Measure each children
        val height = when (index) {
            0 -> constraints.maxHeight * 0.8f
            1 -> constraints.maxHeight * 0.9f
            2 -> constraints.maxHeight * 0.6f
            else -> 0f
        }
        measurable.measure(
            constraints.copy(
                minWidth = 0,
                minHeight = 0,
                maxWidth = constraints.maxWidth / 10, // raindrop width
                maxHeight = height.toInt(),
            )
        )
    }

    // Set the size of the layout as big as it can
    layout(constraints.maxWidth, constraints.maxHeight) {
        var xPosition = constraints.maxWidth / ((placeables.size + 1) * 2)

        // Place children in the parent layout
        placeables.forEachIndexed { index, placeable ->
            // Position item on the screen
            placeable.place(x = xPosition, y = 0)

            // Record the y co-ord placed up to
            xPosition += (constraints.maxWidth / ((placeables.size + 1) * 0.8f)).roundToInt()
        }
    }
}}




@Composable
fun rainDrop(modifier: Modifier = Modifier,
             durationMillis: Int = 1000  ) {

    //InfiniteTransition:  0f ~ 1f
    val animateTween by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis, easing = LinearEasing),
            RepeatMode.Restart //start anim
        )
    )

    Canvas(modifier) {

        // scope ： range of drawing
        val width = size.width
        val x: Float = size.width / 2

        val scopeHeight = size.height - width / 2

        // gap of 2 lines
        val space = size.height / 2.2f + width / 2
        val spacePos = scopeHeight * animateTween
        val sy1 = spacePos - space / 2
        val sy2 = spacePos + space / 2

        // line length
        val lineHeight = scopeHeight - space

        // line1
        val line1y1 = max(0f, sy1 - lineHeight)
        val line1y2 = max(line1y1, sy1)

        // line2
        val line2y1 = min(sy2, scopeHeight)
        val line2y2 = min(line2y1 + lineHeight, scopeHeight)

        // draw
        drawLine(
            Color.White,
            Offset(x, line1y1),
            Offset(x, line1y2),
            strokeWidth = width,
            colorFilter = ColorFilter.tint(
                Color.White
            ),
            cap = StrokeCap.Round
        )

        drawLine(
            Color.White,
            Offset(x, line2y1),
            Offset(x, line2y2),
            strokeWidth = width,
            colorFilter = ColorFilter.tint(
                Color.White
            ),
            cap = StrokeCap.Round
        )
    }

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Greeting(
    homeViewModel: HomeViewModel = viewModel()
) {
    //permissionstatefordialog
    var permissionDialog by remember { mutableStateOf(  false) }

    // declare a global variable of FusedLocationProviderClient
    lateinit var fusedLocationClient: FusedLocationProviderClient

    // in onCreate() initialize FusedLocationProviderClient
    fusedLocationClient = LocationServices.getFusedLocationProviderClient( LocalContext.current)
    //Request Location Permission

    if (ActivityCompat.checkSelfPermission(
            LocalContext.current,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            LocalContext.current,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        var permissionsState = rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )
        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(
            key1 = lifecycleOwner,
            effect = {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_START) {
                        permissionsState.launchMultiplePermissionRequest()

                    }
                }

                lifecycleOwner.lifecycle.addObserver(observer)

                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }

            }

        )
        permissionDialog=permissionsState.allPermissionsGranted

    }
     fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
        override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token

        override fun isCancellationRequested() = false
    })
        .addOnSuccessListener { location: android.location.Location? ->
            if (location == null)
                permissionDialog=true

            else {
                val lat = location.latitude.toString()
                val lon = location.longitude.toString()
                Log.e("Location", lon+""+lat)
                homeViewModel.getWeather("$lat,$lon")


            }

        }



    var weatherDegree by remember { mutableStateOf("22") }
    var backgroundImageState by remember { mutableStateOf("https://s3-alpha-sig.figma.com/img/e60f/9823/5589712f1ea25df942ef54ec21572aba?Expires=1673222400&Signature=EjmuSkxPYT8iE16urdcjTPJ7FJQLgv9nPJya3NAUjQJvWyAygQ0SmBONU9MuwE19wsWHNZoVofKOeTZFazN4Pewc8rAcllIhRlYROpF7cwkOnCqL4L9FmtIWuDJrcZ9htFEDZfWEQ7baGcdxVs9XTH-zBQG~9wr6nvb45Is3Lmjy2Bi0mA~bTCKQ0yqxV7g0h6xowN~LXuXS0peRf9RqqJ0cHuC0n-t9QER4CKqRGY27LhLvlPMaD~3f59WbBpQqmoqQsenS~fShgynvUzceoGOR2reEAJpJ73g4YqqAwR~3gZ8OUhA4tH9F-i-57biYTtJD~TpuVVgtyttlP-ujuQ__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4") }

    val weather by homeViewModel.weatherResp.observeAsState()
    val weatherForcast by homeViewModel.forcastweatherResp.observeAsState()


    weatherDegree = weather?.current?.tempC.toString()
    var backgroundColor =(if (true) colorResource(id = R.color.SunnyYellow) else colorResource(id = R.color.CloudyBlue1))
    var textcolorColor =(if (true) colorResource(id = R.color.white) else colorResource(id = R.color.CloudyBlue1Text))

    if (weatherDegree!="null"){




        val backgroundColor2 by animateColorAsState (if (weatherDegree.toFloat() <= 19 ) colorResource(id = R.color.CloudyBlue1) else colorResource(id = R.color.SunnyYellow))
        val textColor2 by animateColorAsState (if (weatherDegree.toFloat() <= 19 ) colorResource(id = R.color.white) else colorResource(id = R.color.SunnyTextYellow))
        textcolorColor=textColor2

        backgroundColor=backgroundColor2
        if (weather!!.current.condition.text.contains("cloud")){
            backgroundImageState="https://s3-alpha-sig.figma.com/img/8f1a/5cc2/b71de89db70cab3375df43a1d5f67691?Expires=1673222400&Signature=Uys7eJG5HCS-mhVb8UZuQ9KsFy4IQMdoo7xVzCD2p9xYpGSQkikwe9Xu2PC8771ln7KB8PVNMuScJW0YVDQbU3rwcCkSZlXjNtMP6DqBzYZFfTjodTOHQhfZ3vQB0aPjndT~pF-qRnWJ0VXqfbuxzO97V3J63s0VkAgu~oKPq6Dofv8jno4K2ivHb1tT5qgPyVPG1lcmv98MF2DUDp30UvCO-NRblB7KtdmXhtliLEfES3VFKLy2z-UVqBDIN1RCgaRpY40OxmYWp03R4N7Ckr~CUVegfQszPf-p-0nAqTmC3Yn99Od7KwC6dQ1bD~VObqjoJnnJ0nXTfXapCI7uHw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        }
        if (weather!!.current.condition.text.contains("rain")){
              backgroundColor =(if (true) colorResource(id = R.color.RainyBlueGreen1) else colorResource(id = R.color.CloudyBlue1))

            backgroundImageState="https://s3-alpha-sig.figma.com/img/7e97/efb6/e6067a078dfccea00cdca2a6444b65a8?Expires=1673222400&Signature=ALDNVkoEBiVP8tu8uDqRG1Bii4tvObGlWYxCZeztjFCaMEWNLwlfQAkLWs7Z-8RSBZF8zJ4yRvvlGVAHcVEtEDFZDYad09T5uEq-8Jx14NAmc20tjAl7j2uEmOwUNTiEDUHM81DQ61XvJ~nTt7iRUiGqOmIqBi-5MfwFY1Znlm0yBeiDw~bH~OR0uLH0fhhbj0mVdCGRW4XglGwClJ~Tsy2ARhgmRXOHrAINqK3MHWo4d3sbQqaCFLBlZU2JtTUKIn-cqJ-BB6P6uaUep83X~j7d9QldlA4V4jjhki1wFj9aAvziM5xHqGf1ng9gnNsh-RrgK-U5Bzmdq4v9YzBYRw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        }

    }






    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(backgroundImageState)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.sunnybackground),
         contentDescription ="test",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )

    Column (Modifier.fillMaxSize()){
        AnimatedVisibility(
            visible = permissionDialog
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = backgroundColor,
                elevation = 4.dp
            ) {
                Text(
                    text = stringResource(R.string.GiveMePermission),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    Box(modifier = Modifier

        .align(CenterHorizontally)
        .padding(top = 50.dp)
        .height(350.dp)
        .width(300.dp)
        .clip(shape = RoundedCornerShape(size = 20.dp))

        .background(backgroundColor)
            ,    contentAlignment = Center
) {

 //  raindroplayout()

        Column(modifier = Modifier.align(TopCenter)) {
        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(15.dp)) {
            Text(  text = "Today",
                color =  textcolorColor,

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
                    .data("https://" + weather?.current?.condition?.icon.toString().drop(2))
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sunnyvector),
                contentDescription = "Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .align(CenterVertically)
                    .padding(10.dp),
            )
            Text(  text = "${weather?.current?.tempC.toString()}°",
                fontSize = 70.sp,
               color =  textcolorColor,
                textAlign = TextAlign.Center
            )

        }
        Row(modifier = Modifier.align(CenterHorizontally),Arrangement.Center) {

            Text(  text = "${weather?.current?.condition?.text}",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color =textcolorColor,
                textAlign = TextAlign.Center
            )

        }
        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 20.dp),Arrangement.Center) {

            Text(  text = "${weather?.location?.region}",
                fontSize = 15.sp,
                color = textcolorColor,
                textAlign = TextAlign.Center

            )

        }
        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 20.dp),Arrangement.Center) {

            Text(  text = "${weather?.location?.localtime}",
                fontSize = 15.sp,
                color = textcolorColor,
                textAlign = TextAlign.Center
            )

        }
        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 20.dp),Arrangement.Center) {

            Text(  text = "Feels like ${weather?.current?.feelslikeC}",
                fontSize = 15.sp,
                color =  textcolorColor,
                textAlign = TextAlign.Center
            ,
              modifier=  Modifier.padding(end = 10.dp)
            )
            Text(  text = "|",
                fontSize = 15.sp,
                color = textcolorColor,
                textAlign = TextAlign.Center
            )
            Text(  text = "Humidity ${weather?.current?.humidity}",
                fontSize = 15.sp,
                color =  textcolorColor,
                textAlign = TextAlign.Center, modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}
        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 20.dp))
            .padding(top = 40.dp)
            .background(backgroundColor.copy(alpha = 0.8f))
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

      }
    }
@Composable
fun QuotePart(
    homeViewModel: HomeViewModel = viewModel()

){
    var toDaysQUOTE by remember { mutableStateOf("Loading") }
    val quote by homeViewModel.quoteResp.observeAsState()
    toDaysQUOTE= quote?.quote.toString() +"\n"+ quote?.author.toString()


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 50.dp),
                Arrangement.Bottom

        ) {


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



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoolWheatherAPPTheme {

      }
}