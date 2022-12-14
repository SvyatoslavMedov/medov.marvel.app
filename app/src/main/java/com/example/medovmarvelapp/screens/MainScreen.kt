package com.example.medovmarvelapp.screens

import Hero
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.example.medovmarvelapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi :: class)
@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.surface
    ) {
        val color = remember {
            mutableStateOf(Color.Gray)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Image(
                painter = painterResource(id = R.drawable.marvel_logo),
                contentDescription = stringResource(
                    id = R.string.logo
                ),
                modifier = Modifier.height(45.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = stringResource(id = R.string.choose_your_hero),
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold),
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(25.dp))
            HorizontalPager(
                count = info.size,
                contentPadding = PaddingValues(32.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        rotate(degrees = 45f) {
                            drawRect(
                                color = color.value,
                                topLeft = Offset(400f, 100f),
                                size = size / 1f
                            )
                        }
                    }) { page ->
                val hero = info[page]
                val bitmap =
                    BitmapFactory.decodeResource(LocalContext.current.resources, hero.drawable)

                val palette = Palette.from(bitmap).generate()
                palette.run {
                    swatches.forEach { swatch ->
                        color.value = Color(swatch.rgb)
                    }

                }
                Card(modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
                    .fillMaxSize(), shape = RoundedCornerShape(15.dp)) {
                    CardOfHero(hero = hero)
                }
            }
        }
    }
}

fun lerp(start: Float, stop : Float, fraction : Float) : Float {
    return (1-fraction) * start + fraction * stop
}

@Preview
@Composable
fun ShowMainScrean(){
    MainScrean()
}

@Composable
fun MainScrean() {
    TODO("Not yet implemented")
}

@Composable
fun CardOfHero(hero:Hero){
    Box(
        Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = hero.drawable),
            contentDescription = stringResource(id = R.string.hero),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Text(text = hero.name, modifier = Modifier
            .align(BottomStart)
                .padding(16.dp),
                maxLines = 1,
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold
                ))
        }
    }

val info = listOf<Hero>(
    Hero(
        id = 0,
        name = "Blade",
        drawable = R.drawable.blade
    ),
    Hero(
        id = 1,
        name = "Gambit",
        drawable = R.drawable.gambit
    ),
    Hero(
        id = 2,
        name = "Magneto",
        drawable = R.drawable.magneto
    ),
    Hero(
        id = 3,
        name = "Spider Man",
        drawable = R.drawable.spidermannoir
    ),
    Hero(
        id = 4,
        name = "Thor",
        drawable = R.drawable.thor
    ),
    Hero(
        id = 5,
        name = "Vision",
        drawable = R.drawable.vision
    ),
    Hero(
        id = 6,
        name = "Wolverine",
        drawable = R.drawable.wolverine
    ),
)


