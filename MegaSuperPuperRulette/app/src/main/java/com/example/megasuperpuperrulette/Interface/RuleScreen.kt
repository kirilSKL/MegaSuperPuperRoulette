package com.example.megasuperpuperrulette.Interface

import android.text.method.NumberKeyListener
import androidx.annotation.Size
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.megasuperpuperrulette.R
import com.example.megasuperpuperrulette.utils.numberUtil
import kotlin.math.roundToInt

@Composable
fun RuleScreen(){

    var rotationValue by remember {
        mutableStateOf(0f)
    }

    var number by remember {
        mutableStateOf(0)
    }

    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
        durationMillis = 2000
        ),
        finishedListener = {
            val index = (360f- (it % 360)) / (360f / numberUtil.list.size)
            number = numberUtil.list[index.roundToInt()]
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,){
    Text(
        text = number.toString(),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentHeight()
            .wrapContentWidth(),

        fontWeight = FontWeight.Bold,
        fontSize = 35.sp,
        color = Color.Black
    )
    Box(modifier = Modifier
        .weight(1f)
        .fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.roulette),
            contentDescription = "Roulette",
            modifier = Modifier
                .fillMaxSize()
                .rotate(angle)
        )
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = "Arrow",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(),
            )
        }
        Button(
            onClick = {
                rotationValue = (720..1080).random().toFloat() + angle
                      },
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Text(
                text = "Start",
                color = Color.LightGray


            )
        }
    }
}

